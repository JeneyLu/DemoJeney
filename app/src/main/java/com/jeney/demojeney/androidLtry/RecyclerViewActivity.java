package com.jeney.demojeney.androidLtry;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeney.demojeney.R;
import com.jeney.demojeney.androidLtry.RecyclerView.DividerItemDecoration;
import com.jeney.demojeney.androidLtry.RecyclerView.Item;
import com.jeney.uicomponent.CustomSwipeRefreshLayout.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

// http://blog.csdn.net/lmj623565791/article/details/45059587
// http://blog.csdn.net/a396901990/article/details/40153759

public class RecyclerViewActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<Item> items;
    private MyAdapter myAdapter;

    public static boolean isStagger;

    private RecyclerViewMode recyclerViewMode;

    public enum RecyclerViewMode {
        VERTICAL_LIST_VIEW, OTHER
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        myAdapter = new MyAdapter(items, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(myAdapter);
        recyclerViewMode = RecyclerViewMode.VERTICAL_LIST_VIEW;
        swipeRefreshLayout.setMode(SwipeRefreshLayout.Mode.BOTH);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setOnLoadListener(this);
        swipeRefreshLayout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initData() {
        items = new ArrayList<>();
        int i = 1;
        while (i <= 10) {
            Item item = new Item();
            item.des = "winter is coming";
            item.imageID = getImageResourceID(this, "p" + i);
            items.add(item);
            i++;
        }
    }

    private int getImageResourceID(Context context, String name) {
        int id = context.getResources().getIdentifier(name, "mipmap", context.getPackageName());
        return id;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recycler_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        isStagger = false;
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                break;
            case R.id.add:
                Item item1 = new Item(R.mipmap.p10, "add");
                myAdapter.add(item1);
                break;
            case R.id.delete:
                myAdapter.delete(1);
                break;
            case R.id.divider:
                if (recyclerViewMode == RecyclerViewMode.VERTICAL_LIST_VIEW)
                    recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider_line), true, true));
                break;
            case R.id.vertical_list_view:
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                recyclerViewMode = RecyclerViewMode.VERTICAL_LIST_VIEW;
                break;
            case R.id.horizontal_list_view:
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                recyclerViewMode = RecyclerViewMode.OTHER;
                break;
            case R.id.grid_view:
                recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
                recyclerViewMode = RecyclerViewMode.OTHER;
                break;
            case R.id.stagger_grid_view:
                isStagger = true;
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
                recyclerViewMode = RecyclerViewMode.OTHER;
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onLoad() {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setLoading(false);
            }
        }, 2000);
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<Item> items;
        private Context context;

        public MyAdapter(List<Item> items, Context context) {
            this.items = items;
            this.context = context;
        }

        public void delete(int position) {
            items.remove(position);
            notifyItemRemoved(position);
        }

        public void add(Item item) {
            items.add(item);
            notifyItemChanged(items.size() - 1);
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.card_view, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            Item item = items.get(position);
            holder.imageView.setImageResource(item.imageID);
            holder.textView.setText(item.des);
            if (isStagger) {
                ViewGroup.LayoutParams layoutParams = holder.cardView.getLayoutParams();
                layoutParams.height = new Random().nextInt(80) + 150;
                holder.cardView.setLayoutParams(layoutParams);
            }
        }

        @Override
        public int getItemCount() {
            return items == null ? 0 : items.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            @Bind(R.id.iv)
            ImageView imageView;
            @Bind(R.id.tv)
            TextView textView;
            @Bind(R.id.card_view)
            CardView cardView;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
