package Week04.class_problems;
import java.util.Arrays;

public class BusRouteRanking {

    static class BusRoute {
        private String routeCode;
        private String routeName;
        private int priority;

        public BusRoute(String routeCode, String routeName, int priority) {
            this.routeCode = routeCode;
            this.routeName = routeName;
            this.priority = priority;
        }

        public BusRoute(String routeCode, String routeName) {
            this(routeCode, routeName, 3);
        }

        public String getRouteCode() {
            return routeCode;
        }

        int compareTo(BusRoute other) {
            if (this.priority != other.priority) {
                return other.priority - this.priority;
            }
            int codeCompare = this.routeCode.compareToIgnoreCase(other.routeCode);
            if (codeCompare != 0) {
                return codeCompare;
            }
            return this.routeName.length() - other.routeName.length();
        }

        static BusRoute[] rankRoutes(BusRoute[] routes) {
            BusRoute[] ranked = Arrays.copyOf(routes, routes.length);
            for (int i = 1; i < ranked.length; i++) {
                BusRoute key = ranked[i];
                int j = i - 1;
                while (j >= 0 && ranked[j].compareTo(key) > 0) {
                    ranked[j + 1] = ranked[j];
                    j--;
                }
                ranked[j + 1] = key;
            }
            return ranked;
        }
    }

    public static void main(String[] args) {
        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = BusRoute.rankRoutes(routes);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < ranked.length; i++) {
            sb.append("\"").append(ranked[i].getRouteCode()).append("\"");
            if (i < ranked.length - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb);
    }
}
