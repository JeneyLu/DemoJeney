package com.jeney.demojeney;

import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.jeney.uicomponent.swipeablecard.model.CardModel;
import com.jeney.uicomponent.swipeablecard.view.CardContainer;
import com.jeney.uicomponent.swipeablecard.view.SimpleCardStackAdapter;


public class SwipeableCardDemoActivity extends ActionBarActivity {

    /**
     * This variable is the container that will host our cards
     */
    private CardContainer mCardContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_swipeable_card_demo);

        mCardContainer = (CardContainer) findViewById(R.id.layoutview);

        Resources r = getResources();

        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(this);

        adapter.add(new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title2", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title3", "Description goes here", r.getDrawable(R.drawable.picture3)));
        adapter.add(new CardModel("Title4", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title5", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title6", "Description goes here", r.getDrawable(R.drawable.picture3)));
        adapter.add(new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title2", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title3", "Description goes here", r.getDrawable(R.drawable.picture3)));
        adapter.add(new CardModel("Title4", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title5", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title6", "Description goes here", r.getDrawable(R.drawable.picture3)));
        adapter.add(new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title2", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title3", "Description goes here", r.getDrawable(R.drawable.picture3)));
        adapter.add(new CardModel("Title4", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title5", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title6", "Description goes here", r.getDrawable(R.drawable.picture3)));
        adapter.add(new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title2", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title3", "Description goes here", r.getDrawable(R.drawable.picture3)));
        adapter.add(new CardModel("Title4", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title5", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title6", "Description goes here", r.getDrawable(R.drawable.picture3)));
        adapter.add(new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title2", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title3", "Description goes here", r.getDrawable(R.drawable.picture3)));
        adapter.add(new CardModel("Title4", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title5", "Description goes here", r.getDrawable(R.drawable.picture2)));

        CardModel cardModel = new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1));
        cardModel.setOnClickListener(new CardModel.OnClickListener() {
            @Override
            public void OnClickListener() {
                Log.i("Swipeable Cards", "I am pressing the card");
            }
        });

        cardModel.setOnCardDismissedListener(new CardModel.OnCardDismissedListener() {
            @Override
            public void onLike() {
                Log.i("Swipeable Cards","I like the card");
            }

            @Override
            public void onDislike() {
                Log.i("Swipeable Cards","I dislike the card");
            }
        });

        adapter.add(cardModel);

        mCardContainer.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_swipeable_card_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
