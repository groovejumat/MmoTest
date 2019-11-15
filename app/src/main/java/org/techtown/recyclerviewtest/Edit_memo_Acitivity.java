package org.techtown.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Edit_memo_Acitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_memo__acitivity);
        Intent intent =getIntent();
        //putdic
        Dictionary getobject = (Dictionary) intent.getSerializableExtra("putdic");
        System.out.println(getobject.getId());
        final int position = getIntent().getIntExtra("position",1);
        System.out.println(position);


        //현재 엑티비티에 있는 에디트 텍스트들을 연결
        final EditText editTextTitle = (EditText)findViewById(R.id.memo_title_ed);
        editTextTitle.setEnabled(false);

        final TextView editTextKorean = (TextView)findViewById(R.id.memo_time_ed);
        final TextView editTextEnglish = (TextView)findViewById(R.id.memo_date_ed);

        final EditText editTextContext = (EditText) findViewById(R.id.memo_context_ed);
        editTextContext.setEnabled(false);

        editTextTitle.setText(getobject.getId());
        editTextEnglish.setText(getobject.getEnglish());
        editTextKorean.setText(getobject.getKorean());
        editTextContext.setText(getobject.getContext());

        Button buttonInsertA = (Button) findViewById(R.id.modify_confirm);
        buttonInsertA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String title = editTextTitle.getText().toString();
                System.out.println(title);

                String english = editTextEnglish.getText().toString();
                System.out.println(english);

                String context = editTextContext.getText().toString();
                System.out.println(context);

                Intent intent = new Intent(getApplication(), MainActivity.class);

                Toast.makeText(getApplicationContext(), "메모가 수정 되었습니다.(메모 수정 객체 및 포지션 값 전달)", Toast.LENGTH_LONG).show();

                final Dictionary putobject= new Dictionary(title,english,"test");
                putobject.setContext(context);

                intent.putExtra("chagedata", putobject);
                intent.putExtra("position",position);

//                //객체의 내용을 보내보기
//                intent.putExtra("title", title);
//                intent.putExtra("english", english);
//                intent.putExtra("context", context);
                startActivity(intent);
            }
        });

        Button buttonInsertB = (Button) findViewById(R.id.edit_mode);
        buttonInsertB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                editTextTitle.setEnabled(true);
                editTextContext.setEnabled(true);
            }
        });

    }
}
