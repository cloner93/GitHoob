package com.milad.githoob.utils.contributions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ContributionsProvider {

    private final static String FILL_STRING = "data-level=\"";
    private final static String DATA_STRING = "data-count=\"";
    private final static String DATE_STRING = "data-date=\"";

    @NonNull
    public List<ContributionsDay> getContributions(@Nullable String string) {
        ArrayList<ContributionsDay> contributions = new ArrayList<>();

        try {
            if (InputHelper.isEmpty(string)) return contributions;
            int fillPos = -1;
            int dataPos = -1;
            int datePos = -1;
            while (true) {
                fillPos = string.indexOf(FILL_STRING, fillPos + 1);
                dataPos = string.indexOf(DATA_STRING, dataPos + 1);
                datePos = string.indexOf(DATE_STRING, datePos + 1);
                if (fillPos == -1) break;
                int level = 0;
                String levelString = string.substring(fillPos + FILL_STRING.length(), fillPos + FILL_STRING.length() + 1);
                switch (levelString) {
                    case "0":
                        level = 0;
                        break;
                    case "1":
                        level = 1;
                        break;
                    case "2":
                        level = 2;
                        break;
                    case "3":
                        level = 3;
                        break;
                    case "4":
                        level = 4;
                        break;
                }

                int dataEndPos = string.indexOf("\"", dataPos + DATA_STRING.length());
                String dataString = string.substring(dataPos + DATA_STRING.length(), dataEndPos);
                int data = Integer.parseInt(dataString);
                String dateString = string.substring(datePos + DATE_STRING.length(), datePos + DATE_STRING.length() + 11);
                contributions.add(new ContributionsDay(
                        Integer.parseInt(dateString.substring(0, 4)),
                        Integer.parseInt(dateString.substring(5, 7)),
                        Integer.parseInt(dateString.substring(8, 10)),
                        level,
                        data
                ));
            }
        } catch (NumberFormatException e) {
            return contributions;
        }

        return contributions;
    }
}
