package example.com.materialtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * 水果的详情活动页面
 * */
public class FruitActivity extends AppCompatActivity {
    public static final String FRUIT_NAME="fruit_name";
    public static final String FRUIT_IMAGE_ID="fruit_image_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        initView();

    }

    private void initView() {
        Intent intent=getIntent();
        String fruitname=intent.getStringExtra("FRUIT_NAME");
        int fruitiamgeid=intent.getIntExtra("FRUIT_IMAGE_ID",0);

        Toolbar mtoobar= (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout mCollapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsingtoolbar_layout);
        ImageView fruitimageview= (ImageView) findViewById(R.id.fruit_iamgeview);
        TextView fruitcontenttext= (TextView) findViewById(R.id.fruit_content_textview);
        setSupportActionBar(mtoobar);

        //启用HomeAsUp按钮
        ActionBar actionbar=getSupportActionBar();
        if (actionbar!=null){
            actionbar.setDisplayHomeAsUpEnabled(true);
        }

        mCollapsingToolbarLayout.setTitle(fruitname);
        Glide.with(this).load(fruitiamgeid).into(fruitimageview);
        String fruitcontent=generateFruitContent(fruitname);
        fruitcontenttext.setText(fruitcontent);
    }

    private String generateFruitContent(String fruitname) {
        StringBuilder fruitcontent=new StringBuilder();
        for(int i=0;i<500;i++){
            fruitcontent.append(fruitname);
        }
        return fruitcontent.toString();
    }

    /**
     * 监听actionBar中的控件
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //当点击HomeAsUp按钮时关闭当前活动
            case android.R.id.home:
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
