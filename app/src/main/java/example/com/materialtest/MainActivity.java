package example.com.materialtest;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FloatingActionButton mFloatingActionButton;
    private RecyclerView mRecyclerView;
    private FruitAdapter mFruitAdapter;
    private List<Fruit> mFruitList=new ArrayList<>();
    private Fruit[] fruits={new Fruit("pingguo",R.drawable.pingguo),new Fruit("chengzi",R.drawable.chengzi),new Fruit("fanqie",R.drawable.fanqie),new Fruit("caomei",R.drawable.caomei)};
    private SwipeRefreshLayout mSwipeRefreshLayout;//刷新控件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //setContentView(R.layout.activity_main);
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
        mFloatingActionButton= (FloatingActionButton) findViewById(R.id.floatingAction_button);
        initFruitList();
        mRecyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        //创建布局管理器
        GridLayoutManager manager=new GridLayoutManager(this,2);
        //给recycleview设置布局
        mRecyclerView.setLayoutManager(manager);
        //初始化适配器
        mFruitAdapter=new FruitAdapter(mFruitList);
        //给recyclerview设置适配器
        mRecyclerView.setAdapter(mFruitAdapter);

        //初始化刷新控件
        mSwipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reFreshFruit();
            }
        });


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

        //解决item图标颜色问题
        mNavigationView.setItemIconTintList(null);
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

        //给浮动按钮设置监听事件
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this, "您点击了浮动按钮！！！", Toast.LENGTH_SHORT).show();
                // 用snackbar代替Toast
                Snackbar.make(v,"您确定要删除吗？",Snackbar.LENGTH_SHORT).setAction("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "已取消删除操作", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
    }

    private void reFreshFruit() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    //重新生成水果图片数据
                        initFruitList();
                        //通知adapter数据发生了变化
                        mFruitAdapter.notifyDataSetChanged();
                        //刷新事件结束后，隐藏刷新进度条
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFruitList() {
        mFruitList.clear();
        for (int i=0;i<50;i++){
            Random mrandom=new Random();
            //随机拿到图片
            int index=mrandom.nextInt(fruits.length);
            mFruitList.add(fruits[index]);
        }
    }
}
