#Java8+#Gradle4+

1)Clone this repository and move to jar folder (yandextranslator/jar). There you can see yandextranslator.jar and profiles folder(for detecting language by langdetect library). Accessible languages are russian and english.
2)Input command "java -jar yandextranslator.jar" and add some text, for example:
      I)java -jar yandextranslator.jar hi
      II)java -jar yandextranslator.jar hi "my name is Mikle"
      III)java -jar yandextranslator.jar привет "i am from Moscow"
      IV)java -jar yandextranslator.jar "как твои дела?" "я сегодня не приеду" "what is your name?"
As you can see to correctly show translated statement you have to input text in "". If you want to translate just one word it is unnecessary to input "", but it is acceptable.
