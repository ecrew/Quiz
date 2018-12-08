package in.techfantasy.qzap.viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Stack;

import in.techfantasy.qzap.BR;
import in.techfantasy.qzap.Models.GameSession;
import in.techfantasy.qzap.Models.Question;
import in.techfantasy.qzap.Network.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameSessionViewModel extends ViewModel {
    private GameSession gameSession;
    private Stack<Integer> idStack = new Stack<>();
    ArrayList<Question> QuestionList;
    private MutableLiveData<ArrayList<Question>> questions;






    @Bindable
    public String toastMessage = null;


    public GameSessionViewModel (){
        gameSession = new GameSession();
        setUp();
    }


    public String getToastMessage() {
        return toastMessage;
    }


    private void setToastMessage(String toastMessage) {

        this.toastMessage = toastMessage;
        //notifyPropertyChanged(BR.toastMessage);
    }


    //Questions Operation
    public void setUp(){
        RestClient.RestApiInterface apiInterface=RestClient.getClient();
        Call<JsonElement> call=apiInterface.getQuestions();
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if(response.isSuccessful()){
                    JsonElement result = response.body();
                    com.google.gson.JsonArray jsonArray=response.body().getAsJsonArray();
                    QuestionList=new ArrayList<Question>();
                    for (JsonElement item:jsonArray) {
                        Question jobj=new Gson().fromJson(item,Question.class);
                        QuestionList.add(jobj);
                        Log.d("ImageUrl:",jobj.toString());
                    }
                   // Log.d("from db:",jsonArray.get(0).toString());

                }
                else{
                    setToastMessage("Error in response:"+response.toString());
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                setToastMessage("Error in Data fetch:"+t.getMessage());
            }
        });
    }


    public ArrayList<Question> getQuestions(){
        return QuestionList;
    }







    //Stack operations start
    public void addIdToStack(int id){
        idStack.push(id);
    }

    public int removeIdFromStack(){
        return idStack.pop();
    }

    public void clearSatck(){
        idStack.clear();
    }



}
