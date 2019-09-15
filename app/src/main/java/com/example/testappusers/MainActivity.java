package com.example.testappusers;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    final String USER_NAME_KEY = "userName";
    final String USER_DATA_ITEM_A = "itemName";
    final String USER_DATA_ITEM_B = "itemData";
    final String USER_DATA_ITEM_C = "itemImage";

    final int ONLINE_IMG = android.R.drawable.presence_online;
    final int OFFLINE_IMG = android.R.drawable.presence_offline;
    final int BANANA = R.drawable.banana;
    final int STRAWBERRY = R.drawable.strawberry;
    final int APPLE = R.drawable.apple;
    final int MALE = R.drawable.male;
    final int FEMALE = R.drawable.female;
    final int GREEN_EYE = R.drawable.greeneye;
    final int BLUE_EYE = R.drawable.blueeye;
    final int BROWN_EYE = R.drawable.redeye;
    final int IMG_NULL = 0;

    Date date = null;
    SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
    SimpleDateFormat sdfFormat = new SimpleDateFormat("HH:mm dd.MM.yy", Locale.ENGLISH);
    String tags = "";
    String friendName = "";
    String onOff = "";
    int sex = IMG_NULL;
    int eyeColor = IMG_NULL;
    int fru = 0;
    List<Integer> friendsId;
    int expGroupId;

    List<User> users;
    ArrayList<Map<String, Object>> groupData;
    ArrayList<Map<String, Object>> childDataItem;
    ArrayList<ArrayList<Map<String, Object>>> childData;
    Map<String, Object> map;
    ExpandableListView elvMain;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                usersCreate();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        readData();

        if (users == null) {
            usersCreate();
        }

        groupData = new ArrayList<>();

        for (User user : users) {
            map = new HashMap<>();
            map.put(USER_NAME_KEY, user.getName());
            groupData.add(map);
        }

        String[] groupFrom = new String[]{USER_NAME_KEY};
        int[] groupTo = new int[]{android.R.id.text1};


        childData = new ArrayList<>();


        for (int i = 0; i < users.size(); i++) {
            childDataItem = new ArrayList<>();
            map = new HashMap<>();
            map.put(USER_DATA_ITEM_A, getString(R.string.ID_Text));
            map.put(USER_DATA_ITEM_B, String.valueOf(users.get(i).getId()));
            map.put(USER_DATA_ITEM_C, IMG_NULL);
            childDataItem.add(map);

            map = new HashMap<>();
            map.put(USER_DATA_ITEM_A, getString(R.string.GUID_Text));
            map.put(USER_DATA_ITEM_B, users.get(i).getGuid());
            map.put(USER_DATA_ITEM_C, IMG_NULL);
            childDataItem.add(map);

            map = new HashMap<>();
            int statusImg;
            if (users.get(i).isActive()) {
                onOff = "Online";
                statusImg = ONLINE_IMG;
            } else {
                onOff = "Offline";
                statusImg = OFFLINE_IMG;
            }
            map.put(USER_DATA_ITEM_A, getString(R.string.Status_Text));
            map.put(USER_DATA_ITEM_B, onOff);
            map.put(USER_DATA_ITEM_C, statusImg);
            childDataItem.add(map);

            map = new HashMap<>();
            map.put(USER_DATA_ITEM_A, getString(R.string.Balance_Text));
            map.put(USER_DATA_ITEM_B, users.get(i).getBalance());
            map.put(USER_DATA_ITEM_C, IMG_NULL);
            childDataItem.add(map);

            map = new HashMap<>();
            map.put(USER_DATA_ITEM_A, getString(R.string.Age_Text));
            map.put(USER_DATA_ITEM_B, String.valueOf(users.get(i).getAge()));
            map.put(USER_DATA_ITEM_C, IMG_NULL);
            childDataItem.add(map);

            map = new HashMap<>();
            if (users.get(i).getEyeColor().equals("blue")) {
                eyeColor = BLUE_EYE;
            } else if (users.get(i).getEyeColor().equals("green")) {
                eyeColor = GREEN_EYE;
            } else if (users.get(i).getEyeColor().equals("brown")) {
                eyeColor = BROWN_EYE;
            }
            map.put(USER_DATA_ITEM_A, getString(R.string.EyeColor_Text));
            map.put(USER_DATA_ITEM_B, "");
            map.put(USER_DATA_ITEM_C, eyeColor);
            childDataItem.add(map);

            map = new HashMap<>();
            if (users.get(i).getGender().equals("male")) {
                sex = MALE;
            } else if (users.get(i).getGender().equals("female")) {
                sex = FEMALE;
            }
            map.put(USER_DATA_ITEM_A, getString(R.string.Gender_Text));
            map.put(USER_DATA_ITEM_B, "");
            map.put(USER_DATA_ITEM_C, sex);
            childDataItem.add(map);

            map = new HashMap<>();
            map.put(USER_DATA_ITEM_A, getString(R.string.Company_Text));
            map.put(USER_DATA_ITEM_B, users.get(i).getCompany());
            map.put(USER_DATA_ITEM_C, IMG_NULL);
            childDataItem.add(map);

            map = new HashMap<>();
            map.put(USER_DATA_ITEM_A, getString(R.string.Email_Text));
            map.put(USER_DATA_ITEM_B, users.get(i).getEmail());
            map.put(USER_DATA_ITEM_C, IMG_NULL);
            childDataItem.add(map);

            map = new HashMap<>();
            map.put(USER_DATA_ITEM_A, getString(R.string.Phone_Text));
            map.put(USER_DATA_ITEM_B, users.get(i).getPhone());
            map.put(USER_DATA_ITEM_C, IMG_NULL);
            childDataItem.add(map);

            map = new HashMap<>();
            map.put(USER_DATA_ITEM_A, getString(R.string.Adress_Text));
            map.put(USER_DATA_ITEM_B, users.get(i).getAddress());
            map.put(USER_DATA_ITEM_C, IMG_NULL);
            childDataItem.add(map);

            map = new HashMap<>();
            map.put(USER_DATA_ITEM_A, getString(R.string.About_Text));
            map.put(USER_DATA_ITEM_B, users.get(i).getAbout());
            map.put(USER_DATA_ITEM_C, IMG_NULL);
            childDataItem.add(map);

            map = new HashMap<>();
            try {
                date = sdfParse.parse(users.get(i).getRegistered());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            map.put(USER_DATA_ITEM_A, getString(R.string.Registered_Text));
            map.put(USER_DATA_ITEM_B, sdfFormat.format(date));
            map.put(USER_DATA_ITEM_C, IMG_NULL);
            childDataItem.add(map);

            map = new HashMap<>();
            map.put(USER_DATA_ITEM_A, getString(R.string.Location_Text));
            map.put(USER_DATA_ITEM_B, users.get(i).getLatitude() + ", " + users.get(i).getLongitude());
            map.put(USER_DATA_ITEM_C, IMG_NULL);
            childDataItem.add(map);

            map = new HashMap<>();
            tags = users.get(i).getTags()[0];
            for (int j = 1; j < users.get(i).getTags().length; j++) {
                tags += ", " + users.get(i).getTags()[j];
            }
            map.put(USER_DATA_ITEM_A, getString(R.string.Tags_Text));
            map.put(USER_DATA_ITEM_B, tags);
            map.put(USER_DATA_ITEM_C, IMG_NULL);
            childDataItem.add(map);

            map = new HashMap<>();
            friendName = users.get(users.get(i).getFriends().get(0).get("id")).getName();
            for (int j = 1; j < users.get(i).getFriends().size(); j++) {
                friendName += ", " + users.get(users.get(i).getFriends().get(j).get("id")).getName();
            }
            map.put(USER_DATA_ITEM_A, getString(R.string.Friends_Text));
            map.put(USER_DATA_ITEM_B, friendName);
            map.put(USER_DATA_ITEM_C, IMG_NULL);
            childDataItem.add(map);

            map = new HashMap<>();
            fru = IMG_NULL;
            if (users.get(i).getFavoriteFruit().equals("banana")) {
                fru = BANANA;
            } else if (users.get(i).getFavoriteFruit().equals("apple")) {
                fru = APPLE;
            } else if (users.get(i).getFavoriteFruit().equals("strawberry")) {
                fru = STRAWBERRY;
            }
            map.put(USER_DATA_ITEM_A, getString(R.string.FavoriteFruit_Text));
            map.put(USER_DATA_ITEM_B, "");
            map.put(USER_DATA_ITEM_C, fru);
            childDataItem.add(map);


            childData.add(childDataItem);
        }


        String[] childFrom = new String[]{USER_DATA_ITEM_A, USER_DATA_ITEM_B, USER_DATA_ITEM_C};
        int[] childTo = new int[]{R.id.tvItemName, R.id.tvItemData, R.id.ivItemImg};


        final ImageSimpleExpandableList sela = new ImageSimpleExpandableList(this, groupData,
                R.layout.activity_main_lay_list_view, groupFrom,
                groupTo, childData, R.layout.activity_child_lay_list_view,
                childFrom, childTo);

        elvMain = findViewById(R.id.elvMain);
        elvMain.setAdapter(sela);
        registerForContextMenu(elvMain);


        elvMain.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(final ExpandableListView parent, View v, final int groupPosition, long id) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        parent.smoothScrollToPositionFromTop(groupPosition, 0);
                    }
                }, 100);
                return false;
            }
        });

        elvMain.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (expGroupId != -1 && groupPosition != expGroupId) {
                    elvMain.collapseGroup(expGroupId);
                }
                expGroupId = groupPosition;
            }
        });

        elvMain.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent;
                switch (childPosition) {
                    case 8:
                        intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + users.get(groupPosition).getEmail()));
                        startActivity(intent);
                        break;
                    case 9:
                        intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + users.get(groupPosition).getPhone()));
                        startActivity(intent);
                        break;
                    case 13:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" +
                                users.get(groupPosition).getLatitude() + "," + users.get(groupPosition).getLongitude()));
                        startActivity(intent);
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        int groupPosition = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int childPosition = ExpandableListView.getPackedPositionChild(info.packedPosition);
        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD && childPosition != 13) {
            friendsId = new LinkedList<>();
            menu.setHeaderTitle("Friends:");
            for (int j = 0; j < users.get(groupPosition).getFriends().size(); j++) {
                menu.add(0, j, 1, users.get(users.get(groupPosition).getFriends().get(j).get("id")).getName());
                friendsId.add(users.get(groupPosition).getFriends().get(j).get("id"));
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) item
                .getMenuInfo();
        ExpandableListView elv = elvMain;

        int type = ExpandableListView.getPackedPositionType(info.packedPosition);

        if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
        } else if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            elv.expandGroup(friendsId.get(item.getItemId()));
            elv.setSelectedGroup(friendsId.get(item.getItemId()));
        }
        return super.onContextItemSelected(item);
    }

    void writeData(List<User> userList) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("data.dat", MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userList);
            fos.close();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readData() {
        FileInputStream fis = null;
        try {
            fis = openFileInput("data.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void usersCreate() {
        InputStream inputStream = null;
        try {
            inputStream = getApplicationContext().getAssets().open("users.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (inputStream == null) {
            throw new AssertionError();
        }
        users = ListUsersCreate.getUserList(inputStream);
        writeData(users);
    }

}
