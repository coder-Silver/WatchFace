package com.tnt.watchhome.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tnt.watchhome.R;
import com.tnt.watchhome.ui.view.CustomWatchFace;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WatchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WatchFragment extends Fragment {

    private static final String TAG = "watchFragment";
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PAGE= "arg_page";
    private static final String ARG_TITLE = "arg_title";



    // TODO: Rename and change types of parameters
    private int  mPage;
    private String mTitle;
    private CustomWatchFace mWatch ;

    private View mView ;

    private Activity mActivity ;

    private OnFragmentInteractionListener mListener;

    private GestureDetector mGestureDetector ;
    private int mScrollDirection ;

    public WatchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param page Parameter 1.
     * @param page Parameter 2.
     * @return A new instance of fragment WatchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WatchFragment newInstance(int page, String title) {
        WatchFragment fragment = new WatchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPage = getArguments().getInt(ARG_PAGE);
            mTitle = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (null == mView) {
            mView = inflater.inflate(R.layout.fragment_watch, container, false);
            mWatch = mView.findViewById(R.id.watch_face) ;
            mWatch.setFragment(this);
        }
        return mView ;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int gestureDirection);
    }


    public void setDirection(int direction) {
        mScrollDirection = direction ;
        notifyDirection() ;
    }
    public int getScrollDirection(){
        return mScrollDirection ;
    }
    public void notifyDirection() {
        if (null == mListener)return ;
        mListener.onFragmentInteraction(mScrollDirection);
    }


}
