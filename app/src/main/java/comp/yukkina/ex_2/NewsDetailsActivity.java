package comp.yukkina.ex_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import comp.yukkina.ex_2.data.NewsItem;
import androidx.appcompat.app.AppCompatActivity;

public class NewsDetailsActivity extends AppCompatActivity {
    private static final String EXTRA_NEWS_ITEM = "extra:newsItem";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        final NewsItem newsItem = (NewsItem) getIntent().getSerializableExtra(EXTRA_NEWS_ITEM);

        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(newsItem.getCategory().getName());
        }

        final ImageView imageView = findViewById(R.id.details_image);
        final TextView titleView = findViewById(R.id.details_title);
        final TextView dateView = findViewById(R.id.details_date);
        final TextView textView = findViewById(R.id.details_text);

        Glide.with(this).load(newsItem.getImageUrl()).into(imageView);
        titleView.setText(newsItem.getTitle());
        textView.setText(newsItem.getFullText());
        dateView.setText(Utils.formatDateTime(this, newsItem.getPublishDate()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static void start(@NonNull Context context, @NonNull NewsItem newsItem) {
        context.startActivity(new Intent(context, NewsDetailsActivity.class).putExtra(EXTRA_NEWS_ITEM, newsItem));
    }
}

