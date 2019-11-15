package org.techtown.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class memoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        //현재 엑티비티에 있는 에디트 텍스트들을 연결
        TextView editTextTitle = (TextView)findViewById(R.id.memo_title);
        //날짜
        TextView editTextEnglish = (TextView)findViewById(R.id.memo_content);
        //시간
        TextView editTextEnglish2 = (TextView)findViewById(R.id.memo_content2);
        TextView editTextContext = (TextView) findViewById(R.id.memo_context);


        //인벤트로 클릭 한 값 가지고 오기.
        //Dictionary getobject =(Dictionary) getIntent().getSerializableExtra("itemdata");

        //put으로 들어온 값을 체크해보기
        String title = (String)getIntent().getStringExtra("title");
        String english = (String)getIntent().getStringExtra("english");
        String english2 = (String)getIntent().getStringExtra("english2");
        String content = (String)getIntent().getStringExtra("context");
        final int position = getIntent().getIntExtra("position",1);
        System.out.println("선택한 위치 값 출력하기" + position);

        //해당 객체의 값들을 모두 가져왔습니다.//
//        String Id = getobject.getId();
//        System.out.println(Id);
//        String english = getobject.getEnglish();
//        System.out.println(english);
//        String korean = getobject.getKorean();
//        System.out.println(korean);
//        String context = getobject.getContext();
//        System.out.println(context);

        editTextTitle.setText(title);
        editTextEnglish.setText(english);
        editTextEnglish2.setText(english2);
        editTextContext.setText(content);

        final Dictionary dic = new Dictionary(title,"의미","없음");
        dic.setContext(content);

        Button buttonInsertA = (Button) findViewById(R.id.edit_context);
        buttonInsertA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Edit_memo_Acitivity.class);
                intent.putExtra("putdic", dic);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });



    }
}
