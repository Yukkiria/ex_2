package comp.yukkina.ex_2;
import comp.yukkina.ex_2.data.DataUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class NewsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        final NewsAdapter adapter = new NewsAdapter(this, newsItem -> NewsDetailsActivity.start(this, newsItem));
        final RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setAdapter(adapter);
        recycler.addItemDecoration(new NewsItemDecoration(getResources().getDimensionPixelSize(R.dimen.spacing_micro)));

        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            final int columnsCount = getResources().getInteger(R.integer.landscape_news_columns_count);
            recycler.setLayoutManager(new GridLayoutManager(this, columnsCount));
        } else {
            recycler.setLayoutManager(new LinearLayoutManager(this));
        }

        adapter.replaceItems(DataUtils.generateNews());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
           case R.id.action_about:
               startActivity(new Intent(this,AboutActivity.class));
               overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            return true;

       }
    return super.onOptionsItemSelected(item);
    }


}
