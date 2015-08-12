package org.mcsg.double0negative.tabapi;

import java.util.HashMap;
import org.bukkit.plugin.Plugin;

public class TabObject {

    private HashMap<Integer, TabHolder> tabs = new HashMap<Integer, TabHolder>();

    public void setPriority(Plugin p, int pri) {
        for (int a = -1; a < 4; a++) {
            if ((this.tabs.get(Integer.valueOf(a)) != null) && (((TabHolder) this.tabs.get(Integer.valueOf(a))).p == p)) {
                this.tabs.put(Integer.valueOf(a), null);
            }
        }
        if (pri > -2) {
            TabHolder t = new TabHolder(p);
            this.tabs.put(Integer.valueOf(pri), t);
        }
    }

    public TabHolder getTab() {
        int a = 3;
        while ((this.tabs.get(Integer.valueOf(a)) == null) && (a > -3)) {
            a--;
        }
        if (a == -2) {
            return new TabHolder(null);
        }

        return (TabHolder) this.tabs.get(Integer.valueOf(a));
    }

    public void setTab(Plugin plugin, int x, int y, String msg, int ping) {
        int a = -1;
        while (((this.tabs.get(Integer.valueOf(a)) == null) || (((TabHolder) this.tabs.get(Integer.valueOf(a))).p != plugin)) && (a < 3)) {
            a++;
        }
        if ((a == 3) && ((this.tabs.get(Integer.valueOf(a)) == null) || (((TabHolder) this.tabs.get(Integer.valueOf(a))).p != plugin))) {
            setPriority(plugin, 0);
            a = 0;
        }

        TabHolder t = (TabHolder) this.tabs.get(Integer.valueOf(a));
        t.tabs[y][x] = msg;
        t.tabPings[y][x] = ping;
        t.maxh = 3;
        t.maxv = Math.max(x + 1, t.maxv);
    }

}
