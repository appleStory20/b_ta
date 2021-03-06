package project001.itcore.it_talk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import project001.itcore.it_talk.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by peten on 2017. 10. 1..
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> msgList;
    private List<String> names;
    private List<String> mTime;
    private List<Integer> mTypeList;
    private Context mContex;
    private int mType=0;

    public ChatAdapter(Context contex, List<String> msgList, List<String> mTime) {
        this.msgList = msgList;
        this.mContex = contex;
        this.mTime = mTime;
        mTypeList = new ArrayList<>();
        names = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_row_user, parent, false);
            return new ChatHolder(v);
        }else if(viewType==1) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_row_other, parent, false);
            return new ChatHolder(v);
        }else if(viewType == 2){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_exit,parent,false);
            return new ExitHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(mType==0){ //our message
            ChatHolder chHolder = (ChatHolder) holder;
            chHolder.msg.setText(msgList.get(position));
            chHolder.time.setText(""+mTime.get(position));
        }else if(mType==1){ //other user's message
            ChatHolder chHolder = (ChatHolder) holder;
            chHolder.msg.setText(msgList.get(position));
            chHolder.time.setText(""+mTime.get(position));
        }else if(mType == 2){ // user join/left/connection/matching message
            String exitText = /*names.get(position)+" "+ */ msgList.get(position);
            if(!exitText.equals(""))
            {
                ExitHolder exitHolder = (ExitHolder) holder;
                exitHolder.name.setText(exitText);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        mType = mTypeList.get(position);
        return mTypeList.get(position);
    }



    @Override
    public int getItemCount() {
        return msgList.size();
    }

    public void addNewMsg(String msg, int type, String time, String name){
        names.add(name);
        msgList.add(msg);
        mTime.add(time);
        mTypeList.add(type);
        notifyDataSetChanged();
    }
}
