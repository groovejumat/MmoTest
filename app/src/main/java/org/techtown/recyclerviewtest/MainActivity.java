package org.techtown.recyclerviewtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Dictionary> mArrayList;
    private CustomAdapter mAdapter;
    private int count = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("msg","11 OnCreate!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //생성할 릴사이클러 뷰의 아이디 값을 세팅하고, 레이아웃 매니저를 통해서 해당 리사이클러뷰를 새로 진행 방식으로 만든다.
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_list);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        //어레이 리스트 생성
        mArrayList = new ArrayList<>();
        Dictionary dict = new Dictionary("시연용", "시연용", "시연용" );
        dict.setContext("이 메모는 시연용 입니다.");
        mArrayList.add(dict);
        mArrayList.add(dict);
        mArrayList.add(dict);
        mArrayList.add(dict);
        mArrayList.add(dict);
        mArrayList.add(dict);
        mArrayList.add(dict);
        mArrayList.add(dict);
        mArrayList.add(dict);
        mArrayList.add(dict);
        mArrayList.add(dict);




        //내가 만든 어댑터 클래스 객체를 생성
        mAdapter = new CustomAdapter(this,mArrayList);
        //mAdapter = new CustomAdapter(this, mArrayList);

        //내가 만든 어댑터를 생성한 리사이클려뷰에 세팅
        mRecyclerView.setAdapter(mAdapter);


        //뷰나누기??
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        //추가하기에서 들여온 데이터를 추가시켜준다
        //intent.getExtras()!=null
        Intent intent = getIntent();
        if(intent.getSerializableExtra("itemdata")!=null) {
            System.out.println("데이터 추가 작업 진행");
            Dictionary getobject = (Dictionary) intent.getSerializableExtra("itemdata");
            System.out.println("추가된 메모장 정보 확인");
            System.out.println(getobject.getEnglish());
            System.out.println(getobject.getKorean());
            System.out.println(getobject.getContext());
            mArrayList.add(getobject);
            mAdapter.notifyItemInserted(0);
        }

        //변경된 데이터가 존재하면(activity_deit_memo로 부터 전달 받음)
        Intent memo_edit_data = getIntent();
        if(intent.getSerializableExtra("chagedata")!=null) {
            System.out.println("데이터 변경 작업 진행");
            Dictionary getobject = (Dictionary)intent.getSerializableExtra("chagedata");
            int position = getIntent().getIntExtra("position",1);
            System.out.println("포지션 값을 받아옴" + position);
            mArrayList.set(position,getobject);
            mAdapter.notifyItemInserted(0);
        }



        //리사이클러 뷰에서 아이템을 터치했을 때의 동작을 처리한다.
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                //아이템을 클릭했을 때에 메모장에 대한 정보를 보내주어야 하니까, 해당 내용에 관련된 dic객체를 생성해서 보내준다.
                Intent intent2 = new Intent(getApplication(), memoActivity.class);
                Dictionary dict = mArrayList.get(position);
                Toast.makeText(getApplicationContext(), dict.getId() + ' ' + dict.getEnglish() + ' ' + dict.getKorean() + position, Toast.LENGTH_LONG).show();
                System.out.println("<<메모장 정보 확인>>");
                System.out.println(dict.getId());
                System.out.println(dict.getEnglish());
                System.out.println(dict.getKorean());
                System.out.println(dict.getContext());
                String a = dict.getId();
                String b = dict.getEnglish();
                String c = dict.getContext();
                String e = dict.getKorean();
                int d = position;

                intent2.putExtra("title", a);
                intent2.putExtra("english", b);
                intent2.putExtra("english2",e);
                intent2.putExtra("context", c);
                intent2.putExtra("position", d);

                startActivity(intent2);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        //엑티비티로 전달 후에 값저장하기 메모내용을 추가해주는 뷰이벤트 처리
        Button buttonInsertA = (Button) findViewById(R.id.button_insert_activity);
        buttonInsertA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddmemoActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });


        //Button buttonInsert = (Button) findViewById(R.id.button_main_insert);
//        buttonInsert.setOnClickListener(new View.OnClickListener() {
//            // 1. 화면 아래쪽에 있는 데이터 추가 버튼을 클릭하면
//            @Override
//            public void onClick(View v) {
//
//
//                //Intent intent = new Intent(getApplication(), memoActivity.class);
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//
//                View view = LayoutInflater.from(MainActivity.this)
//                        .inflate(R.layout.edit_box, null, false);
//                builder.setView(view);
//
//
//                final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit);
//                final EditText editTextID = (EditText) view.findViewById(R.id.edittext_dialog_id);
//                final EditText editTextEnglish = (EditText) view.findViewById(R.id.edittext_dialog_endlish);
//                final EditText editTextKorean = (EditText) view.findViewById(R.id.edittext_dialog_korean);
//
//                ButtonSubmit.setText("삽입");
//
//                final AlertDialog dialog = builder.create();
//
//
//                // 3. 다이얼로그에 있는 삽입 버튼을 클릭하면
//
//                ButtonSubmit.setOnClickListener(new View.OnClickListener() {
//
//
//                    public void onClick(View v) {
//
//
//                        // 4. 사용자가 입력한 내용을 가져와서
//                        String strID = editTextID.getText().toString();
//                        String strEnglish = editTextEnglish.getText().toString();
//                        String strContext = editTextKorean.getText().toString();
//
//
//                        // 5. ArrayList에 추가하고
//
//                        Dictionary dict = new Dictionary(strID, strEnglish, "애교용" );
//                        dict.setContext(strContext);
//                        mArrayList.add(0, dict); //첫번째 줄에 삽입됨
//                        //mArrayList.add(dict); //마지막 줄에 삽입됨
//
//
//                        // 6. 어댑터에서 RecyclerView에 반영하도록 합니다.
//
//                        mAdapter.notifyItemInserted(0);
//                        //mAdapter.notifyDataSetChanged();
//
//                        dialog.dismiss();
//                    }
//                });
//
//                dialog.show();
//            }
//        });
    }




    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private MainActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MainActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }

}
