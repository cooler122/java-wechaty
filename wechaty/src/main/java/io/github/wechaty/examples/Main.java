package io.github.wechaty.examples;


import io.github.wechaty.MessageListener;
import io.github.wechaty.ScanListener;
import io.github.wechaty.Wechaty;
import io.github.wechaty.filebox.FileBox;
import io.github.wechaty.user.Contact;
import io.github.wechaty.user.Room;
import io.github.wechaty.utils.QrcodeUtils;
import okhttp3.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;


public class Main {

    private static OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {

        FileBox fileBox = FileBox.fromUrl("https://img.xilidou.com/img/dong.jpg", null, null);

        Wechaty bot = Wechaty.instance("donut-test-user-3016");

        bot.on("scan", (qrcode, statusScanStatus, data) -> {
            System.out.println(QrcodeUtils.getQr(qrcode));
        });

        bot.on("message", (MessageListener) message -> {

            Contact from = message.from();
            Room room = message.room();

            String text = message.text();

            if (StringUtils.equals(text, "#ding")) {
                if (room != null) {
                    room.say("dong");
                } else {
                    from.say("dong");
                }
            }
        });

        bot.on("room-join", (room, inviteeList, inviter, date) -> {
            List<String> nameList = inviteeList.stream().map(Contact::name).collect(Collectors.toList());
            room.say("欢迎" + nameList + "加入群聊");

        });
        bot.start(true);
//    }

//        Room room = bot.room();
//
//        RoomQueryFilter roomQueryFilter = new RoomQueryFilter();
//
//        roomQueryFilter.setTopic("ChatOps - Donut");
//
//        Future<List<Room>> all = room.findAll(roomQueryFilter);
//
//        List<Room> rooms = all.get();
//
//        Room room1 = rooms.get(0);
//
//        FileBox fileBox = FileBox.fromFile("dong.jpg", "dong.jpg");
//
//        room1.say(fileBox).get();

    }



}
