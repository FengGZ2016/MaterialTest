package example.com.materialtest;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        setContentView(R.layout.drawerlayout);
        initView();
    }

    /**
     * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。
     * 返回true则显示该menu,false 则不显示;
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载toolbar菜单文件
    getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    /**
     * 菜单项被点击时调用，也就是菜单项的监听方法。
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
        case android.R.id.home:
            //将滑动菜单显示出来
            mDrawerLayout.openDrawer(GravityCompat.START);
            break;
        case R.id.backup:
            Toast.makeText(this, "你点击了backup键", Toast.LENGTH_SHORT).show();
            break;
        case R.id.delete:
            Toast.makeText(this, "你点击了delete键", Toast.LENGTH_SHORT).show();
            break;
        case R.id.setting:
            Toast.makeText(this, "你点击了setting键", Toast.LENGTH_SHORT).show();
            break;
        default:
    }

        return true;
    }

    private void initView() {
    mToolbar= (Toolbar) findViewById(R.id.main_toolbar);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView= (NavigationView) findViewById(R.id.nav_view);
        //将toolbar的实例传入
        setSupportActionBar(mToolbar);
        //获取actionbar
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
           //让导航按钮显示出来
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置一个导航栏图标
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
        }
    //将call的item选项设置为默认选中
    mNavigationView.setCheckedItem(R.id.item_call);

        //设置一个菜单项选中事件的监听器
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //当点击任意菜单项时，关闭滑动菜单
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }
}