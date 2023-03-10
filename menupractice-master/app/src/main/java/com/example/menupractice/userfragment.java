package com.example.menupractice;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class userfragment extends Fragment {
    private static String TAG = "phptest_secondActivity";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public userfragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static userfragment newInstance(String param1, String param2) {
        userfragment fragment = new userfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    MainActivity activity;
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    String[] sendDB = new String[7];
    Button btnC, btnK1, btnK2, btnF, btnM, btnW, btnSend;
    EditText editN;

    Button bigCity, choongchung, gyungi, jeonla, kangwon, gyungsang, btnGo;
    Button cityWeatherFind;
    TextView cityWeather, weatherKind;
    String url, city, weather, msg;

    Button weatherFoodSuggest, userFoodAdd;
    LinearLayout lr1, lr2;

    final Bundle bundle = new Bundle();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String bundleMessage = bundle.getString("message");
            String[] splitBundle = bundleMessage.split(" ");
            Log.d("split : ", splitBundle[3]);
            weatherKind.setText(splitBundle[3]);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.auau, container, false);
        btnC = (Button) rootView.findViewById(R.id.btncategory);
        btnK1 = (Button) rootView.findViewById(R.id.btnkeyword1);
        btnK2 = (Button) rootView.findViewById(R.id.btnkeyword2);
        btnF = (Button) rootView.findViewById(R.id.btnflavor);
        btnM = (Button) rootView.findViewById(R.id.btnmood);
        btnW = (Button) rootView.findViewById(R.id.btnweather);
        btnSend = (Button) rootView.findViewById(R.id.btnsend);
        editN = (EditText) rootView.findViewById(R.id.edtname);

        bigCity = (Button) rootView.findViewById(R.id.bigCity);
        choongchung = (Button) rootView.findViewById(R.id.choongchung);
        gyungi = (Button) rootView.findViewById(R.id.gyunggi);
        jeonla = (Button) rootView.findViewById(R.id.jeonla);
        kangwon = (Button) rootView.findViewById(R.id.kangwon);
        gyungsang = (Button) rootView.findViewById(R.id.gyungsang);
        btnGo = (Button) rootView.findViewById(R.id.gogo);

        cityWeather = (TextView) rootView.findViewById(R.id.cityWeather);

        cityWeatherFind = (Button) rootView.findViewById(R.id.cityWeatherFind);
        weatherKind = (TextView) rootView.findViewById(R.id.weatherKind);

        weatherFoodSuggest = (Button) rootView.findViewById(R.id.weatherFoodSuggest);
        userFoodAdd = (Button) rootView.findViewById(R.id.userFoodAdd);

        lr1 = (LinearLayout) rootView.findViewById(R.id.weatherLinear);
        lr2 = (LinearLayout) rootView.findViewById(R.id.MyFoodAddLinear);

        weatherFoodSuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lr1.setVisibility(View.VISIBLE);
                lr2.setVisibility(View.INVISIBLE);
            }
        });
        userFoodAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lr1.setVisibility(View.INVISIBLE);
                lr2.setVisibility(View.VISIBLE);
            }
        });

        bigCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] selectedItem = {""};
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                dlg.setTitle("????????? ???????????????!!!");
                dlg.setIcon(R.drawable.applogo);
                String[] words = new String[] {"???????????????", "???????????????", "???????????????", "???????????????", "???????????????", "???????????????", "???????????????", "?????????????????????"};
                dlg.setSingleChoiceItems(words, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = words[which];
                    }
                }).setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cityWeather.setText(selectedItem[0]);
                    }
                });
                dlg.show();
            }
        });
        choongchung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] selectedItem = {""};
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                dlg.setTitle("????????? ???????????????!!!");
                dlg.setIcon(R.drawable.applogo);
                String[] words = new String[] {"??????", "??????", "??????", "??????", "??????", "??????", "??????", "??????", "??????", "??????", "??????"};
                dlg.setSingleChoiceItems(words, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = words[which];
                    }
                }).setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cityWeather.setText(selectedItem[0]);
                    }
                });
                dlg.show();
            }
        });
        gyungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] selectedItem = {""};
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                dlg.setTitle("????????? ???????????????!!!");
                dlg.setIcon(R.drawable.applogo);
                String[] words = new String[] { "??????", "??????", "??????", "??????", "??????", "??????", "??????", "??????", "??????"};
                dlg.setSingleChoiceItems(words, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = words[which];
                    }
                }).setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cityWeather.setText(selectedItem[0]);
                    }
                });
                dlg.show();
            }
        });
        jeonla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] selectedItem = {""};
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                dlg.setTitle("????????? ???????????????!!!");
                dlg.setIcon(R.drawable.applogo);
                String[] words = new String[] {"??????", "??????", "??????", "??????", "??????", "??????", "??????", "??????", "??????", "??????", "??????"};
                dlg.setSingleChoiceItems(words, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = words[which];
                    }
                }).setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cityWeather.setText(selectedItem[0]);
                    }
                });
                dlg.show();
            }
        });
        kangwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] selectedItem = {""};
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                dlg.setTitle("????????? ???????????????!!!");
                dlg.setIcon(R.drawable.applogo);
                String[] words = new String[] {"??????", "??????", "??????", "??????", "??????", "??????", "??????", "??????"};
                dlg.setSingleChoiceItems(words, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = words[which];
                    }
                }).setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cityWeather.setText(selectedItem[0]);
                    }
                });
                dlg.show();
            }
        });
        gyungsang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] selectedItem = {""};
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                dlg.setTitle("????????? ???????????????!!!");
                dlg.setIcon(R.drawable.applogo);
                String[] words = new String[] {"??????", "??????", "??????", "??????", "??????", "??????", "??????", "??????", "??????"};
                dlg.setSingleChoiceItems(words, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = words[which];
                    }
                }).setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cityWeather.setText(selectedItem[0]);
                    }
                });
                dlg.show();
            }
        });



        cityWeatherFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            url = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=";
                            city = cityWeather.getText().toString();
                            weather = "+??????";
                            String wholeURL = url + city + weather;
                            doc = (Document) Jsoup.connect(wholeURL).get();
                            Element elements = doc.select(".temperature_info").first();
                            Log.d("Elements : ", elements.toString());
                            msg = elements.text();
                            bundle.putString("message", msg);
                            Message msg = handler.obtainMessage();
                            msg.setData(bundle);
                            handler.sendMessage(msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CcActivity.class);
                intent.putExtra("Kind", weatherKind.getText());
                startActivity(intent);
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] selectedItem = {""};
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                dlg.setTitle("????????? ???????????????!!!");
                dlg.setIcon(R.drawable.applogo);
                String[] words = new String[] {"??????", "??????", "??????", "??????", "?????????"};
                dlg.setSingleChoiceItems(words, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = words[which];
                    }
                }).setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendDB[1] = selectedItem[0];
                        btnC.setText(selectedItem[0]);
                    }
                });
                dlg.show();
            }
        });

        btnK1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] selectedItem = {""};
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                dlg.setTitle("????????? ???????????????!!!");
                dlg.setIcon(R.drawable.applogo);
                String[] words = new String[] {"??????", "?????????", "???", "???", "???", "???", "??????", "???"};
                dlg.setSingleChoiceItems(words, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = words[which];
                    }
                }).setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendDB[2] = selectedItem[0];
                        btnK1.setText(selectedItem[0]);
                    }
                });
                dlg.show();
            }
        });

        btnK2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] selectedItem = {""};
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                dlg.setTitle("????????? ???????????????!!!");
                dlg.setIcon(R.drawable.applogo);
                String[] words = new String[] {"??????", "???", "???", "??????", "??????", "???", "??????",
                        "??????", "??????", "??????", "???????????????", "??????", "??????", "??????", "??????"};
                dlg.setSingleChoiceItems(words, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = words[which];
                    }
                }).setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendDB[3] = selectedItem[0];
                        btnK2.setText(selectedItem[0]);
                    }
                });
                dlg.show();
            }
        });

        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] selectedItem = {""};
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                dlg.setTitle("????????? ???????????????!!!");
                dlg.setIcon(R.drawable.applogo);
                String[] words = new String[] {"??????", "????????????", "?????????", "??????", "????????????","????????????","??????"};
                dlg.setSingleChoiceItems(words, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = words[which];
                    }
                }).setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendDB[4] = selectedItem[0];
                        btnF.setText(selectedItem[0]);
                    }
                });
                dlg.show();
            }
        });

        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] selectedItem = {""};
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                dlg.setTitle("????????? ???????????????!!!");
                dlg.setIcon(R.drawable.applogo);
                String[] words = new String[] {"??????","??????"};
                dlg.setSingleChoiceItems(words, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = words[which];
                    }
                }).setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendDB[5] = selectedItem[0];
                        btnM.setText(selectedItem[0]);
                    }
                });
                dlg.show();
            }
        });

        btnW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] selectedItem = {""};
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                dlg.setTitle("????????? ???????????????!!!");
                dlg.setIcon(R.drawable.applogo);
                String[] words = new String[] {"??????", "????????????", "??????", "??????", "???","???"};
                dlg.setSingleChoiceItems(words, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = words[which];
                    }
                }).setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendDB[6] = selectedItem[0];
                        btnW.setText(selectedItem[0]);

                    }
                });
                dlg.show();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDB[0] = editN.getText().toString();
                Intent intent = new Intent(activity, AaActivity.class);
                intent.putExtra("Insert", sendDB);
                startActivity(intent);
            }
        });
        return rootView;
    }

}