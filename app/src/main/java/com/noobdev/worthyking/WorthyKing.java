package com.noobdev.worthyking;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/* Worthy King II
 You are the last successor of a worthy clan whose name is "P",
 You are in your kingdom with no troops but your barracks train "X" troops every day. Initially X= 1
 There are N neighbour kingdom, each of which has A[i] amount of troops guarding it. To take over a kingdom you need to attack it with at least A[i] troops.
 Once you capture a kingdom, as a consequence of war, all of your troops die(even if you attacked with > A[i]) and X increases by 1.

 constant
 1<=N<=20
 1<=A[i]<= 10^6

 Format
 argument is an integer array A**/

public class WorthyKing implements Interface.WorthyKingInterface {
    Interface.MainInterface mainInterface;

    public WorthyKing(Interface.MainInterface mainInterface) {
        this.mainInterface = mainInterface;
    }

    String TAG = "worthy";

    public class Army {
        int army;

        public Army(int army) {
            this.army = army;
        }

        public int getArmy() {
            return army;
        }

        public void setArmy(int army) {
            this.army = army;
        }
    }

    public class Nation {
        private List<Army> armyList;

        public Nation(List<Army> armyList) {
            this.armyList = armyList;
        }

        public List<Army> getArmyList() {
            return armyList;
        }

        public void setArmyList(List<Army> armyList) {
            this.armyList = armyList;
        }
    }

    private List<Nation> nationList1 = new ArrayList<>();

    int day = 0;
    int troop = 0;
    int troopNeeded = 0;
    int x = 1;

    @Override
    public void initialClass() {
        List<Army> armyList = new ArrayList<>(); //cre
        armyList.add(new Army(1));
        armyList.add(new Army(2));
        armyList.add(new Army(3));
        armyList.add(new Army(4));

        nationList1.add(new Nation(armyList));
        nationList1.add(new Nation(armyList));
        nationList1.add(new Nation(armyList));
        nationList1.add(new Nation(armyList));
        nationList1.add(new Nation(armyList));
        //increase no nation

        Log.d(TAG, "no of nations: " + nationList1.size());
        Capture(nationList1);

    }

    public void Capture(List<Nation> nationList) {
        for (int n = 1; n < nationList.size() + 1; n++) {
            Log.d(TAG, "barack army each day: " + x + " with nation: " + n);
            for (int i = 1; i < nationList.get(n - 1).armyList.size() + 1; i++) {
                //  Log.d(TAG, "troop no: " + i + " with nation: " + n);
                troopNeeded = troopNeeded + nationList.get(n - 1).armyList.get(i - 1).army;

                if (i == nationList.get(n - 1).armyList.size()) { //at last
                    Log.d(TAG, "troopNeeded: " + String.valueOf(troopNeeded));
                    int withDay = 0;
                    while (true) {
                        troop = troop + x;
                        day = day + 1;
                        withDay += 1;
                        if (troop >= troopNeeded) { //war ended with one nation
                            Log.d(TAG, "troop:  " + troop + " troopNeeded: " + troopNeeded);
                            troop = 0; // casualty all troop died
                            troopNeeded = 0;
                            Log.d(TAG, "war ended with nation: " + n + " within days: " + withDay);
                            break;
                        }
                    }
                }
            }
            x = x + 1;
            if (n == nationList.size()) {
                mainInterface.onGetWorthyKing(day);
                Log.d(TAG, "total number of days: " + String.valueOf(day));
                break;
            }
//            else {
//                Log.d(TAG,"nation: "+ n+ "  days: "+ String.valueOf(day));
//            }
        }
    }
}
