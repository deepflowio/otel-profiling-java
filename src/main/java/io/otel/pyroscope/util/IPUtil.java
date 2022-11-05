/*
 * Copyright (c) 2022 Yunshan Networks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.otel.pyroscope.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class IPUtil {
    private final static String localHostStr = "localhost";

    private final static String localHostNum = "127.0.0.1";
    private final static  List<String> IPV4S = new ArrayList<>(1);

    public static List<String> getIPV4s() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = interfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress address = inetAddresses.nextElement();
                    if (address instanceof Inet4Address) {
                        String addressStr = address.getHostAddress();
                        if (localHostNum.equals(addressStr)) {
                            continue;
                        } else if (localHostStr.equals(addressStr)) {
                            continue;
                        }
                        IPV4S.add(addressStr);
                    }
                }
            }
        } catch (SocketException e) {
            //ignore.
        }
        return IPV4S;
    }

    public static String getIPV4() {
        final List<String> ips = getIPV4s();
        if (ips.size() > 0) {
            return ips.get(0);
        } else {
            return "none";
        }
    }
}
