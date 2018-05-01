package com.example.grant.groupk;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class ChatRoom extends AppCompatActivity {

    private EditText editMess;
    private DatabaseReference mDatabase;
    private RecyclerView messageList;

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test2, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_home:
                startActivity(new Intent(this, Homepage.class));
                return true;
            case R.id.action_catalog:
                startActivity(new Intent (this, CatalogMain.class));
                return true;
            case R.id.action_profile:
                startActivity(new Intent (this, ProfilePage.class));
                return true;
            case R.id.action_settings:
                startActivity(new Intent (this, Setting.class));
                return true;
            case R.id.action_chat:
                startActivity(new Intent (this, ChatRoom.class));
                return true;
            case R.id.action_calender:
                startActivity(new Intent (this, CalendarActivity.class));
                return true;
            case R.id.action_map:
                startActivity(new Intent (this, MapsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_room);

        editMess = (EditText)findViewById(R.id.editMessage);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Message");
        messageList = (RecyclerView) findViewById(R.id.messageRecycle);


        messageList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        messageList.setLayoutManager(linearLayoutManager);

    }





    public void sendMessageButtonClick(View v){


        String messageValue = editMess.getText().toString().trim();
        if (!TextUtils.isEmpty(messageValue)){
            DatabaseReference newPost = mDatabase.push();
            newPost.child("content").setValue(messageValue);
        }
    }


    protected void onStart(){
        super.onStart();

        FirebaseRecyclerAdapter <Message, MessageViewHolder> FBRA = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(
                Message.class,
                R.layout.send_message,
                MessageViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder, Message model, int position) {
                viewHolder.setContent(model.getContent());
            }
        };
        messageList.setAdapter(FBRA);
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public MessageViewHolder(View v){
            super(v);
            mView = v;
        }
        public void setContent (String content){
            TextView message_content = (TextView) mView.findViewById(R.id.text_message);
            message_content.setText(content);
        }

    }


}
