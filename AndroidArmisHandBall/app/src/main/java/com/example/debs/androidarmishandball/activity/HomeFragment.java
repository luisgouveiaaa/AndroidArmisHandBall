package com.example.debs.androidarmishandball.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.debs.androidarmishandball.Game;
import com.example.debs.androidarmishandball.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Game[]> {
        @Override
        protected Game[] doInBackground(Void... params) {
            RestProperties webProperties = new RestProperties(HomeFragment.this.getContext());
            final UriComponents uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                    .host(webProperties.getHost()).port(webProperties.getPort()).path(webProperties.getUsersUri() + "/"
                            + AppSession.loggedUser.getUsername() + webProperties.getTimeslotsUri() + "/all"
                    ).build();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, "Basic " + AppSession.getEncondedUserCredentials());

            HttpEntity<TimeSlotDTO[]> request = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<TimeSlotDTO[]> result = restTemplate.exchange(uri.toUri(), HttpMethod.GET, request, TimeSlotDTO[].class);

            TimeSlotDTO[] objects = result.getBody();

            return objects;
        }

        @Override
        protected void onPostExecute(TimeSlotDTO[] bookings) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.next_booking_view);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            TimeSlotDTO nextBooking[] = new TimeSlotDTO[1];
            if (bookings.length > 0) {
                nextBooking[0] = bookings[0];
                /*for(int i = 1; i < bookings.length; i++){
                    if(nextBooking[0].getDateISO8601().compareTo(bookings[i].getDateISO8601())<0
                            || (nextBooking[0].getDateISO8601().compareTo(bookings[i].getDateISO8601())== 0
                            && nextBooking[0].getBeginHourISO8601().compareTo(bookings[i].getBeginHourISO8601())>0)){
                        nextBooking[0] = bookings[i];
                    }
                }*/
                recyclerView.setAdapter(new AccessTokensAdapter(HomeFragment.this.getContext(), nextBooking));
            }
        }
    }

}


