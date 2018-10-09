package comp.yukkina.ex_2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.a_text));
        ImageButton vk=findViewById(R.id.vk);
        ImageButton insta=findViewById(R.id.insta);
        ImageButton tg=findViewById(R.id.tg);
        final EditText text = findViewById(R.id.edt_txt_message);
        final Button emButton=findViewById(R.id.btn_send);
        vk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                webpage(getString(R.string.vk_url));
            }
        });
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webpage(getString(R.string.insta_url));
            }
        });
        tg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webpage(getString(R.string.tg_url));
            }
        });

        emButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    msg=text.getText().toString();
                    open_Email("Hi",msg);
                }
                catch (ActivityNotFoundException e){
                    Toast.makeText(getApplicationContext(),R.string.err,Toast.LENGTH_LONG).show();
                }
            }
        });
        addView();

    }
    private void addView() {
        LinearLayout liglav = findViewById(R.id.li_glav);
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setText(R.string.footer_text);
        liglav.addView(textView);
    }

    public void webpage(String url){
        try {
            Uri web=Uri.parse(url);
            Intent intent=new Intent(Intent.ACTION_VIEW,web);
            if (intent.resolveActivity(getPackageManager())!=null){
                startActivity(intent);
            }
        }
        catch (ActivityNotFoundException e){
            Toast.makeText(this, R.string.err, Toast.LENGTH_LONG).show();
        }
    }
    private void open_Email(String subject,String msg) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:andr.academy.msk@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, msg);
        if (intent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, R.string.err, Toast.LENGTH_LONG).show();
            return;
        }
        startActivity(intent);
    }

}
