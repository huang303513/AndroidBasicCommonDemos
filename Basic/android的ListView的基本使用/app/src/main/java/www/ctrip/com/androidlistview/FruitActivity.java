package www.ctrip.com.androidlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FruitActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<Fruit>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(FruitActivity.this, R.layout.fruit_item,fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    private void initFruits() {
        Fruit apple = new Fruit("Apple", R.drawable.apple_pic); fruitList.add(apple);
        Fruit banana = new Fruit("Banana", R.drawable.banana_pic); fruitList.add(banana);
        Fruit orange = new Fruit("Orange", R.drawable.orange_pic); fruitList.add(orange);
        Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic); fruitList.add(watermelon);
        Fruit pear = new Fruit("Pear", R.drawable.pear_pic); fruitList.add(pear);
        Fruit grape = new Fruit("Grape", R.drawable.grape_pic); fruitList.add(grape);
        Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic); fruitList.add(pineapple);
        Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic); fruitList.add(strawberry);
        Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic); fruitList.add(cherry);
        Fruit mango = new Fruit("Mango", R.drawable.mango_pic); fruitList.add(mango);
    }
}
