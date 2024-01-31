package com.example.chack;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link pastRecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pastRecordFragment extends Fragment {

    private RecyclerView recyclerView;
    private PastRecFragAdapter adapter = new PastRecFragAdapter();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public pastRecordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment pastRecordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static pastRecordFragment newInstance(String param1, String param2) {
        pastRecordFragment fragment = new pastRecordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        Context ct = this.getContext(); //프래그먼트의 context
        View v =  inflater.inflate(R.layout.fragment_past_record, container, false); //프래그먼트 레이아웃(xml)을 가져와 뷰객체 생성

        //생성한 뷰객체를 이용해 리사이클러뷰를 초기화
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        //스크롤 방향 세로로 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(ct, RecyclerView.VERTICAL, false));

        //리사이클러 뷰에 객체 10개 생성해 줌 (추후 서재에 있는 책 목록으로 객체 생성 하게끔 수정 필요)
        /*
        for(int i = 0; i < 1; i++)
        {
            BookItemRecord item = new BookItemRecord();
            item.image = R.drawable.img_2;
            item.rating = 5;
            item.name = "세이노의 가르침";
            item.writer = "세이노";
            item.isbn13 = "9791168473690";
            item.setStartingDate(2023, 4, 20);
            item.setEndDate(2023, 4, 26);
            item.pub = "데이원";
            adapter.setArrayData(item);
        }
         */
        BookItemRecord item = new BookItemRecord();
        item.image = R.drawable.img_2;
        item.rating = 5;
        item.name = "세이노의 가르침";
        item.writer = "세이노";
        item.isbn13 = "9791168473690";
        item.setStartingDate(2023, 4, 20);
        item.setEndDate(2023, 4, 26);
        item.pub = "데이원";
        adapter.setArrayData(item);

        BookItemRecord item2 = new BookItemRecord();
        item2.image = R.drawable.img_6;
        item2.rating = (float)4.5;
        item2.name = "메리골드 마음 세탁소";
        item2.writer = "윤정은";
        item2.isbn13 = "9791191891287";
        item2.setStartingDate(2023, 3, 10);
        item2.setEndDate(2023, 3, 19);
        item2.pub = "북로망스";
        adapter.setArrayData(item2);

        BookItemRecord item3 = new BookItemRecord();
        item3.image = R.drawable.img_5;
        item3.rating = (float)4;
        item3.name = "데일 카네기 인간 관계론";
        item3.writer = "데일 카네기";
        item3.isbn13 = "9788960542518";
        item3.setStartingDate(2023, 3, 28);
        item3.setEndDate(2023, 4, 6);
        item3.pub = "중앙경제평론사";
        adapter.setArrayData(item3);

        BookItemRecord item4 = new BookItemRecord();
        item4.image = R.drawable.img_4;
        item4.rating = (float)3.5;
        item4.name = "THE WOK 더 웍 - 웍으로 이어가는 주방 과학의 모든 것";
        item4.writer = "J. 켄지 로페즈 알트 (지은이),셰프크루 (옮긴이)";
        item4.isbn13 = "9788931466164";
        item4.setStartingDate(2023, 4, 7);
        item4.setEndDate(2023, 4, 11);
        item4.pub = "영진.com(영진닷컴)";
        adapter.setArrayData(item4);

        //커스텀 이벤트 리스너 객체를 생성하여 어댑터에 전달
        adapter.setOnItemClickListener(new AddBookFragAdapter.OnItemClickListener()
        {
            //아이템 클릭시
            @Override
            public void onItemClick(View v, int position)
            {
                DataClass.searchText = adapter.getItemPosition(position).isbn13;
                //addBookFragment생성
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, new addBookFragment()).commitAllowingStateLoss();
            }
        });

        recyclerView.setAdapter(adapter);  //리사이클러 뷰에 어댑터 설정

        return v;
    }
}




//읽은책 프래그먼트 리사이클러뷰의 어댑터 클래스
class PastRecFragAdapter extends RecyclerView.Adapter<PastRecFragAdapter.PastRecFragViewHolder>
{

    private ArrayList<BookItemRecord> arrayList;  //읽은 책 정보를 담아놓을 리스트
    private AddBookFragAdapter.OnItemClickListener mListener = null; //setOnItemClickListener메소드로 전달된 객체를 저장할 변수(mListener)

    //생성자
    public PastRecFragAdapter()
    {
        arrayList = new ArrayList<>();
    }

    //뷰 홀더를 생성하는 매소드 ex)리스트 목록이 10개 라면, 위아래 버퍼를 고려해 13~15번 호출된다)
    public PastRecFragViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //읽은책 아이템 레이아웃을 인플레이트하여 뷰홀더 생성
        View view = inflater.inflate(R.layout.past_book_item_record, parent, false);
        PastRecFragViewHolder viewHolder = new PastRecFragViewHolder(context, view);

        //생성한 뷰홀더 리턴
        return viewHolder;
    }

    //앞에 있던 객체가 스크롤되어 안보이게 되면 그 객체를 뒤로 불러와 데이터를 재설정해주는 메소드
    public void onBindViewHolder(PastRecFragViewHolder holder, int position)
    {
        BookItemRecord item = arrayList.get(position);  //리스트에서 책정보 가져와서 item에 넣음
        holder.image.setImageResource(item.image); //가져온 소스로 이미지 뷰의 소스 설정
        holder.name.setText(item.name);  //가져온 텍스트로 책이름 설정
        holder.writer.setText(item.writer);  //가져온 텍스트로 작가 설정
        holder.date.setText(item.getStartingDate() + " ~ " + item.getEndDate());  //날짜 설정
        holder.rating.setRating(item.rating);  //가져온 평점으로 평점 설정
        holder.pub.setText(item.pub); //출판사 설정
    }

    //이미지소스 리스트의 개수를 리턴해주는 메소드
    public int getItemCount()
    {
        return  arrayList.size();
    }

    //position을 입력받아 해당 위치의 아이템 정보를 리턴해줌
    public BookItemRecord getItemPosition(int pos)
    {
        return arrayList.get(pos);
    }

    //이미지소스 리스트에 새로운 소스를 추가해주는 메소드
    public void setArrayData(BookItemRecord data)
    {
        arrayList.add(data);
    }

    //커스텀 리스너 인터페이스 정의
    public interface OnItemClickListener
    {
        void onItemClick(View v, int position);
    }

    //리스너 객체를 전달하는 메소드(setOnItemClickListener)
    public void setOnItemClickListener(AddBookFragAdapter.OnItemClickListener listener)
    {
        this.mListener = listener;
    }




    //읽은책 프래그먼트 리사이클러뷰 뷰홀더 클래스
    class PastRecFragViewHolder extends RecyclerView.ViewHolder
    {

        public ImageView image;
        public TextView name, writer, date, pub;
        public RatingBar rating;

        //생성자
        PastRecFragViewHolder(Context context, View itemView)
        {
            super(itemView);

            //책이미지, 이름, 작가, 날짜, 레이팅바 인플레이트
            image = itemView.findViewById(R.id.BookImgView);
            name = itemView.findViewById(R.id.bookName);
            writer = itemView.findViewById(R.id.bookWriter);
            date = itemView.findViewById(R.id.readDate);
            rating = itemView.findViewById(R.id.rating);
            pub = itemView.findViewById(R.id.bookPub);

            //리사이클러뷰의 아이템 클릭 이벤트 (클릭하면 addbook 프래그먼트 생성하게끔)
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = getAdapterPosition(); //어댑터 내 아이템의 위치를 리턴해주는 메소드
                    if (pos != RecyclerView.NO_POSITION)
                    {
                        if(mListener != null)
                            mListener.onItemClick(v, pos);
                    }
                }
            });
        }
    }
}