package in.techfantasy.qzap;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.techfantasy.qzap.Models.Question;
import in.techfantasy.qzap.viewModels.GameSessionViewModel;
import in.techfantasy.qzap.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private GameSessionViewModel gameSessionViewModel;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main);
       // gameSessionViewModel = new GameSessionViewModel();
        gameSessionViewModel=ViewModelProviders.of(this).get(GameSessionViewModel.class);
        activityMainBinding.setViewModel(gameSessionViewModel);
        activityMainBinding.executePendingBindings();
       // setContentView(R.layout.activity_main);
        //gameSessionViewModel.setUp();
        progressBar=new ProgressBar(this);
        progressBar.setVisibility(View.VISIBLE);
        List<Question> Qlist=  new ArrayList<>();
               // Qlist=gameSessionViewModel.getQuestions();
        Qlist.add(new Question("1","1",1));
        Qlist.add(new Question("1","2",1));
        Qlist.add(new Question("1","3",1));
        Qlist.add(new Question("1","4",1));

        for (Question item : Qlist) {
            renderQuestion(item.getAnswer());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }



    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    private void showProgressBar(boolean showprogress){
        if(showprogress){
            progressBar.setIndeterminate(true);
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setIndeterminate(true);
            progressBar.setVisibility(View.GONE);
        }
    }

    private void renderQuestion(String answer){

        int k=0;

        final Button btnDel=findViewById(R.id.btnDel);
        btnDel.setEnabled(false);
        final EditText etxtAns = findViewById(R.id.etxtAns);
        LinearLayout ll = findViewById(R.id.ll);
        String word=answer;
        int wordcount = word.trim().length();
        int val=wordcount;
        int btnCnt=0;
        if(wordcount<=5){
            btnCnt=wordcount;
        }else{
            btnCnt=5;
        }
        Display display = ((WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth()/5;
        for(int j=0;j<wordcount;j=j+5) {
            LinearLayout lld = new LinearLayout(this);
            lld.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));

            for (int i = 0; i < btnCnt; i++) {
                final Button etxt = new Button(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT);
                etxt.setText( word.trim().split("(?!^)")[k]);
                String[] arr=word.trim().split("(?!^)");
                etxt.setLayoutParams(lp);
                etxt.setId(k);
                k=k+1;
                lld.addView(etxt);
                etxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        etxtAns.append(etxt.getText());
                        etxt.setEnabled(false);
                        gameSessionViewModel.addIdToStack(etxt.getId());

                    }
                });
            }
            ll.addView(lld);
            val=val-5;
            if(val>5){
                btnCnt=5;
            }else{
                if(val>0){
                    btnCnt=val;
                }else {
                    btnCnt = val + 5;
                }
            }
        }
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt = etxtAns.getText().toString();
                etxtAns.setText(txt.substring(0,txt.length()-1));
                Button btn = findViewById(gameSessionViewModel.removeIdFromStack());
                btn.setEnabled(true);
            }
        });
        etxtAns.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()>0){
                    btnDel.setEnabled(true);
                }else{
                    btnDel.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
