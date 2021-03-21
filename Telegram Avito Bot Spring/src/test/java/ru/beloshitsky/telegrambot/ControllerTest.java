package ru.beloshitsky.telegrambot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.beloshitsky.telegrambot.controller.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
  @Autowired MockMvc mockMvc;
  @Autowired Controller controller;

  @Test
  public void recieveUpdateHelpMessageTest() throws Exception {
    String response =
        "SendMessage(chatId=1114716719, text=Введите город, потом товар, например:\n"
            + "Питер iphone 12 pro max, parseMode=null, disableWebPagePreview=null, "
            + "disableNotification=null, replyToMessageId=null, "
            + "replyMarkup=ReplyKeyboardMarkup(keyboard=[[KeyboardButton(text=Помощь, "
            + "requestContact=null, requestLocation=null, requestPoll=null)]], resizeKeyboard=null, "
            + "oneTimeKeyboard=null, selective=null), entities=null, allowSendingWithoutReply=null)";
    String request =
        "Update(updateId=374143261, message=Message(messageId=1944, from=User(id=1114716719, "
            + "firstName=Егор, isBot=false, lastName=Белошицкий, userName=Kirtu38, "
            + "languageCode=ru, canJoinGroups=null, canReadAllGroupMessages=null, "
            + "supportInlineQueries=null), date=1616265028, chat=Chat(id=1114716719, type=private, "
            + "title=null, firstName=Егор, lastName=Белошицкий, userName=Kirtu38, "
            + "allMembersAreAdministrators=null, photo=null, description=null, inviteLink=null, "
            + "pinnedMessage=null, stickerSetName=null, canSetStickerSet=null, permissions=null, "
            + "slowModeDelay=null, bio=null, linkedChatId=null, location=null), forwardFrom=null,"
            + " forwardFromChat=null, forwardDate=null, text=Помощь, entities=null, "
            + "captionEntities=null, audio=null, document=null, photo=null, sticker=null, "
            + "video=null, contact=null, location=null, venue=null, animation=null, "
            + "pinnedMessage=null, newChatMembers=[], leftChatMember=null, newChatTitle=null, "
            + "newChatPhoto=null, deleteChatPhoto=null, groupchatCreated=null, replyToMessage=null, "
            + "voice=null, caption=null, superGroupCreated=null, channelChatCreated=null, "
            + "migrateToChatId=null, migrateFromChatId=null, editDate=null, game=null, "
            + "forwardFromMessageId=null, invoice=null, successfulPayment=null, videoNote=null, "
            + "authorSignature=null, forwardSignature=null, mediaGroupId=null, "
            + "connectedWebsite=null, passportData=null, forwardSenderName=null, poll=null, "
            + "replyMarkup=null, dice=null, viaBot=null, senderChat=null, "
            + "proximityAlertTriggered=null), inlineQuery=null, chosenInlineQuery=null, "
            + "callbackQuery=null, editedMessage=null, channelPost=null, editedChannelPost=null, "
            + "shippingQuery=null, preCheckoutQuery=null, poll=null, pollAnswer=null)";
    mockMvc
        .perform(post("/").content(request))
        .andDo(print());
  }
}
