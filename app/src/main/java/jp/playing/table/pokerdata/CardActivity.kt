package jp.playing.table.pokerdata

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_card.*
import kotlinx.android.synthetic.main.activity_hand.*
import kotlinx.android.synthetic.main.activity_playing.*

class CardActivity : AppCompatActivity() {

    private var mTurn: Turn? = null

    private var mHand: Hand? = null

    private var mMember: Member? = null

    private lateinit var mRealm: Realm

    private var cardSelect = ""

    private var cardSuit = ""

    private var cardNumber1 = ""

    private var cardNumber2 = ""

    private var roundPlayer = ""

    private var roundCheck = ""

    private var memberNum = 0
    private var game_id = 0
    private var count = 0
    private var round = ""
    private var round_count = 0
    private var roundNum = 0
    private var myRound = 0
    private var cardHand1 = ""
    private var cardHand2 = ""
    private var cardCom1 = ""
    private var cardCom2 = ""
    private var cardCom3 = ""
    private var cardCom4 = ""
    private var cardCom5 = ""
    private var bigBlind = 0
    private var tableChips = 0
    private var tableTotalChips = 0
    private var startNum = 0
    private var flopNum = 0
    private var preFlopNum = 0
    private var playingNum = 0
    private var foldPlayer = 0
    private var sameChipsPlayer = 0

    private var cardComSet1 = ""
    private var cardComSet2 = ""
    private var cardComSet3 = ""
    private var cardComSet4 = ""
    private var cardComSet5 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        //Realmの設定
        mRealm = Realm.getDefaultInstance()

        val intent = intent
        memberNum = intent.getIntExtra("memberNum", 0)
        game_id = intent.getIntExtra("game_id", 0)
        count = intent.getIntExtra("count", 0)
        roundCheck = intent.getStringExtra("round")
        round_count = intent.getIntExtra("round_count", 0)
        roundNum = intent.getIntExtra("roundNum", 0)
        myRound = intent.getIntExtra("myRound", 0)
        cardHand1 = intent.getStringExtra("cardHand1")
        cardHand2 = intent.getStringExtra("cardHand2")
        cardCom1 = intent.getStringExtra("cardCom1")
        cardCom2 = intent.getStringExtra("cardCom2")
        cardCom3 = intent.getStringExtra("cardCom3")
        cardCom4 = intent.getStringExtra("cardCom4")
        cardCom5 = intent.getStringExtra("cardCom5")
        bigBlind = intent.getIntExtra("bigBlind", 0)
        tableChips = intent.getIntExtra("tableChips", 0)
        tableTotalChips = intent.getIntExtra("tableTotalChips", 0)
        flopNum = intent.getIntExtra("flopNum", 0)
        preFlopNum = intent.getIntExtra("preFlopNum", 0)
        playingNum = intent.getIntExtra("playingNum", 0)
        foldPlayer = intent.getIntExtra("foldPlayer", 0)
        sameChipsPlayer = intent.getIntExtra("sameChipsPlayer", 0)

        if (roundCheck == "showdown") {
            when {
                cardCom1 == "" && cardCom2 == "" && cardCom3 == "" && cardCom4 == "" && cardCom5 == "" -> round = "flop"
                cardCom1 != "" && cardCom2 != "" && cardCom3 != "" && cardCom4 == "" && cardCom5 == "" -> round = "turn"
                cardCom1 != "" && cardCom2 != "" && cardCom3 != "" && cardCom4 != "" && cardCom5 == "" -> round = "river"
                cardCom1 != "" && cardCom2 != "" && cardCom3 != "" && cardCom4 != "" && cardCom5 != "" -> {
                    intentShowDown()
                }
            }
        } else {
            round = roundCheck
        }

        if (round == "flop") {
            startNum = flopNum
        } else {
            startNum = preFlopNum
        }

        Log.d("kotlintest", cardHand1)
        Log.d("kotlintest", cardHand2)

        cardSetting1()
        cardSetting2()

        when (round) {
            "flop" -> {
                cardSelect = "com1"
                cardDoneButton.text = "1枚目決定"
            }
            "turn" -> {
                cardGetSetting1()
                cardGetSetting2()
                cardGetSetting3()
                cardSelect = "com4"
                cardDoneButton.text = "4枚目決定"
            }
            "river" -> {
                cardGetSetting1()
                cardGetSetting2()
                cardGetSetting3()
                cardGetSetting4()
                cardSelect = "com5"
                cardDoneButton.text = "5枚目決定"
            }
        }

        cardSuitHeart.setOnClickListener {
            cardSuit = "heart"

            if (cardSuit != "" && cardNumber1 != "") {
                when (cardSelect) {
                    "com1" -> cardComSetting1()
                    "com2" -> cardComSetting2()
                    "com3" -> cardComSetting3()
                    "com4" -> cardComSetting4()
                    "com5" -> cardComSetting5()
                }
            }
        }

        cardSuitSpade.setOnClickListener {
            cardSuit = "spade"

            if (cardSuit != "" && cardNumber1 != "") {
                when (cardSelect) {
                    "com1" -> cardComSetting1()
                    "com2" -> cardComSetting2()
                    "com3" -> cardComSetting3()
                    "com4" -> cardComSetting4()
                    "com5" -> cardComSetting5()
                }
            }
        }

        cardSuitClub.setOnClickListener {
            cardSuit = "club"

            if (cardSuit != "" && cardNumber1 != "") {
                when (cardSelect) {
                    "com1" -> cardComSetting1()
                    "com2" -> cardComSetting2()
                    "com3" -> cardComSetting3()
                    "com4" -> cardComSetting4()
                    "com5" -> cardComSetting5()
                }
            }
        }

        cardSuitDiamond.setOnClickListener {
            cardSuit = "diamond"

            if (cardSuit != "" && cardNumber1 != "") {
                when (cardSelect) {
                    "com1" -> cardComSetting1()
                    "com2" -> cardComSetting2()
                    "com3" -> cardComSetting3()
                    "com4" -> cardComSetting4()
                    "com5" -> cardComSetting5()
                }
            }
        }

        cardButton1.setOnClickListener {
            if (cardNumber1 != "1") {
                cardNumber1 = "1"
            } else {
                if (cardNumber2 == "") {
                    cardNumber2 = "1"
                } else {
                    cardNumber2 = ""
                    cardNumber1 = "1"
                }
            }

            if (cardSuit != "" && cardNumber1 != "") {
                when (cardSelect) {
                    "com1" -> cardComSetting1()
                    "com2" -> cardComSetting2()
                    "com3" -> cardComSetting3()
                    "com4" -> cardComSetting4()
                    "com5" -> cardComSetting5()
                }
            }
        }

        cardButton2.setOnClickListener {
            if (cardNumber1 != "1") {
                cardNumber1 = "2"
            } else {
                if (cardNumber2 == "") {
                    cardNumber2 = "2"
                } else {
                    cardNumber2 = ""
                    cardNumber1 = "2"
                }
            }

            if (cardSuit != "" && cardNumber1 != "") {
                when (cardSelect) {
                    "com1" -> cardComSetting1()
                    "com2" -> cardComSetting2()
                    "com3" -> cardComSetting3()
                    "com4" -> cardComSetting4()
                    "com5" -> cardComSetting5()
                }
            }
        }

        cardButton3.setOnClickListener {
            if (cardNumber1 != "1") {
                cardNumber1 = "3"
            } else {
                if (cardNumber2 == "") {
                    cardNumber2 = "3"
                } else {
                    cardNumber2 = ""
                    cardNumber1 = "3"
                }
            }

            if (cardSuit != "" && cardNumber1 != "") {
                when (cardSelect) {
                    "com1" -> cardComSetting1()
                    "com2" -> cardComSetting2()
                    "com3" -> cardComSetting3()
                    "com4" -> cardComSetting4()
                    "com5" -> cardComSetting5()
                }
            }
        }

        cardButton4.setOnClickListener {
            cardNumber1 = "4"
            cardNumber2 = ""

            if (cardSuit != "" && cardNumber1 != "") {
                when (cardSelect) {
                    "com1" -> cardComSetting1()
                    "com2" -> cardComSetting2()
                    "com3" -> cardComSetting3()
                    "com4" -> cardComSetting4()
                    "com5" -> cardComSetting5()
                }
            }
        }

        cardButton5.setOnClickListener {
            cardNumber1 = "5"
            cardNumber2 = ""

            if (cardSuit != "" && cardNumber1 != "") {
                when (cardSelect) {
                    "com1" -> cardComSetting1()
                    "com2" -> cardComSetting2()
                    "com3" -> cardComSetting3()
                    "com4" -> cardComSetting4()
                    "com5" -> cardComSetting5()
                }
            }
        }

        cardButton6.setOnClickListener {
            cardNumber1 = "6"
            cardNumber2 = ""

            if (cardSuit != "" && cardNumber1 != "") {
                when (cardSelect) {
                    "com1" -> cardComSetting1()
                    "com2" -> cardComSetting2()
                    "com3" -> cardComSetting3()
                    "com4" -> cardComSetting4()
                    "com5" -> cardComSetting5()
                }
            }
        }

        cardButton7.setOnClickListener {
            cardNumber1 = "7"
            cardNumber2 = ""

            if (cardSuit != "" && cardNumber1 != "") {
                when (cardSelect) {
                    "com1" -> cardComSetting1()
                    "com2" -> cardComSetting2()
                    "com3" -> cardComSetting3()
                    "com4" -> cardComSetting4()
                    "com5" -> cardComSetting5()
                }
            }
        }

        cardButton8.setOnClickListener {
            cardNumber1 = "8"
            cardNumber2 = ""

            if (cardSuit != "" && cardNumber1 != "") {
                when (cardSelect) {
                    "com1" -> cardComSetting1()
                    "com2" -> cardComSetting2()
                    "com3" -> cardComSetting3()
                    "com4" -> cardComSetting4()
                    "com5" -> cardComSetting5()
                }
            }
        }

        cardButton9.setOnClickListener {
            cardNumber1 = "9"
            cardNumber2 = ""

            if (cardSuit != "" && cardNumber1 != "") {
                when (cardSelect) {
                    "com1" -> cardComSetting1()
                    "com2" -> cardComSetting2()
                    "com3" -> cardComSetting3()
                    "com4" -> cardComSetting4()
                    "com5" -> cardComSetting5()
                }
            }
        }

        cardButton0.setOnClickListener {
            if (cardNumber1 == "1") {
                cardNumber2 = "0"
            }

            if (cardSuit != "" && cardNumber1 != "") {
                when (cardSelect) {
                    "com1" -> cardComSetting1()
                    "com2" -> cardComSetting2()
                    "com3" -> cardComSetting3()
                    "com4" -> cardComSetting4()
                    "com5" -> cardComSetting5()
                }
            }
        }

        cardDaleteButton.setOnClickListener {
            when (round) {
                "flop" -> {
                    cardCom1 = ""
                    cardCom2 = ""
                    cardCom3 = ""
                    cardSuit = ""
                    cardNumber1 = ""
                    cardNumber2 = ""

                    cardComImageView1.setImageResource(R.drawable.card_back)
                    cardComImageView2.setImageResource(R.drawable.card_back)
                    cardComImageView3.setImageResource(R.drawable.card_back)

                    cardSelect = "com1"
                    cardDoneButton.text = "1枚目決定"

                    cardComSet1 = ""
                    cardComSet2 = ""
                    cardComSet3 = ""
                }

                "turn" -> {
                    cardCom4 = ""
                    cardSuit = ""
                    cardNumber1 = ""
                    cardNumber2 = ""

                    cardComImageView4.setImageResource(R.drawable.card_back)

                    cardDoneButton.text = "4枚目決定"

                    cardComSet4 = ""
                }

                "river" -> {
                    cardCom5 = ""
                    cardSuit = ""
                    cardNumber1 = ""
                    cardNumber2 = ""

                    cardComImageView5.setImageResource(R.drawable.card_back)

                    cardDoneButton.text = "5枚目決定"

                    cardComSet5 = ""
                }
            }
        }

        cardDoneButton.setOnClickListener {
            if (roundCheck != "showdown") {
                when (round) {
                    "flop" -> {
                        when (cardSelect) {
                            "com1" -> {
                                if (cardComSet1 == "set") {
                                    cardSelect = "com2"
                                    cardDoneButton.text = "2枚目決定"
                                    cardCom1 = cardSuit + cardNumber1 + cardNumber2
                                }
                            }

                            "com2" -> {
                                if (cardComSet2 == "set") {
                                    cardSelect = "com3"
                                    cardDoneButton.text = "3枚目決定"
                                    cardCom2 = cardSuit + cardNumber1 + cardNumber2
                                }
                            }

                            "com3" -> {
                                Log.d("kotlintest", "flop通過")
                                Log.d("kotlintest", "通過1")
                                if (cardComSet3 == "set") {
                                    Log.d("kotlintest", "通過2")
                                    cardCom3 = cardSuit + cardNumber1 + cardNumber2

                                    Log.d("kotlintest", "通過3")

                                    val handRealmResults = mRealm.where(Hand::class.java).findAll()
                                    Log.d("kotlintest", "通過3-1")
                                    val hand =
                                        mRealm.where(Hand::class.java).equalTo("game_id", game_id)
                                            .findAll()
                                    Log.d("kotlintest", hand.toString())
                                    Log.d("kotlintest", "通過3-2")
                                    val handId = hand.max("id")!!.toInt()
                                    Log.d("kotlintest", "通過3-3")
                                    val handDB =
                                        mRealm.where(Hand::class.java).equalTo("id", handId)
                                            .findFirst()
                                    Log.d("kotlintest", "通過3-4")
                                    mRealm.beginTransaction()
                                    Log.d("kotlintest", "通過3-5")
                                    mHand = Hand()
                                    Log.d("kotlintest", "通過3-6")
                                    handDB!!.table1 = cardCom1
                                    Log.d("kotlintest", "通過3-7")
                                    handDB!!.table2 = cardCom2
                                    Log.d("kotlintest", "通過3-8")
                                    handDB!!.table3 = cardCom3
                                    Log.d("kotlintest", "通過3-9")
                                    mRealm.copyToRealmOrUpdate(mHand!!)
                                    Log.d("kotlintest", "通過3-10")
                                    mRealm.commitTransaction()

                                    Log.d("kotlintest", "通過4")
                                    intentSet()
                                }
                            }
                        }
                    }

                    "turn" -> {
                        Log.d("kotlintest", "turn通過")
                        if (cardComSet4 == "set") {
                            cardCom4 = cardSuit + cardNumber1 + cardNumber2

                            val handRealmResults = mRealm.where(Hand::class.java).findAll()
                            val hand =
                                mRealm.where(Hand::class.java).equalTo("game_id", game_id).findAll()
                            val handId = hand.max("id")!!.toInt()
                            val handDB = handRealmResults.get(handId)
                            mRealm.beginTransaction()
                            mHand = Hand()
                            handDB!!.table4 = cardCom4
                            mRealm.copyToRealmOrUpdate(mHand!!)
                            mRealm.commitTransaction()

                            intentSet()
                        }
                    }

                    "river" -> {
                        Log.d("kotlintest", "river通過")
                        if (cardComSet5 == "set") {
                            cardCom5 = cardSuit + cardNumber1 + cardNumber2

                            val handRealmResults = mRealm.where(Hand::class.java).findAll()
                            val hand =
                                mRealm.where(Hand::class.java).equalTo("game_id", game_id).findAll()
                            val handId = hand.max("id")!!.toInt()
                            val handDB = handRealmResults.get(handId)
                            mRealm.beginTransaction()
                            mHand = Hand()
                            handDB!!.table5 = cardCom5
                            mRealm.copyToRealmOrUpdate(mHand!!)
                            mRealm.commitTransaction()

                            intentSet()
                        }
                    }
                }
            } else {
                when (round) {
                    "flop" -> {
                        when (cardSelect) {
                            "com1" -> {
                                if (cardComSet1 == "set") {
                                    cardSelect = "com2"
                                    cardDoneButton.text = "2枚目決定"
                                    cardCom1 = cardSuit + cardNumber1 + cardNumber2
                                }
                            }

                            "com2" -> {
                                if (cardComSet2 == "set") {
                                    cardSelect = "com3"
                                    cardDoneButton.text = "3枚目決定"
                                    cardCom1 = cardSuit + cardNumber1 + cardNumber2
                                }
                            }

                            "com3" -> {
                                if (cardComSet3 == "set") {
                                    cardSelect = "com4"
                                    cardDoneButton.text = "4枚目決定"
                                    cardCom1 = cardSuit + cardNumber1 + cardNumber2
                                }
                            }

                            "com4" -> {
                                if (cardComSet4 == "set") {
                                    cardSelect = "com5"
                                    cardDoneButton.text = "5枚目決定"
                                    cardCom1 = cardSuit + cardNumber1 + cardNumber2
                                }
                            }

                            "com5" -> {
                                if (cardComSet5 == "set") {
                                    cardCom5 = cardSuit + cardNumber1 + cardNumber2

                                    val handRealmResults = mRealm.where(Hand::class.java).findAll()
                                    val hand = mRealm.where(Hand::class.java).equalTo("game_id", game_id).findAll()
                                    val handId = hand.max("id")!!.toInt()
                                    val handDB = handRealmResults.get(handId)
                                    mRealm.beginTransaction()
                                    mHand = Hand()
                                    handDB!!.table1 = cardCom1
                                    handDB!!.table2 = cardCom2
                                    handDB!!.table3 = cardCom3
                                    handDB!!.table4 = cardCom4
                                    handDB!!.table5 = cardCom5
                                    mRealm.copyToRealmOrUpdate(mHand!!)
                                    mRealm.commitTransaction()

                                    intentShowDown()
                                }
                            }
                        }
                    }

                    "turn" -> {
                        when (cardSelect) {
                            "com4" -> {
                                if (cardComSet4 == "set") {
                                    cardSelect = "com5"
                                    cardDoneButton.text = "5枚目決定"
                                    cardCom1 = cardSuit + cardNumber1 + cardNumber2
                                }
                            }

                            "com5" -> {
                                if (cardComSet5 == "set") {
                                    cardCom5 = cardSuit + cardNumber1 + cardNumber2

                                    val handRealmResults = mRealm.where(Hand::class.java).findAll()
                                    val hand = mRealm.where(Hand::class.java).equalTo("game_id", game_id).findAll()
                                    val handId = hand.max("id")!!.toInt()
                                    val handDB = handRealmResults.get(handId)
                                    mRealm.beginTransaction()
                                    mHand = Hand()
                                    handDB!!.table4 = cardCom4
                                    handDB!!.table5 = cardCom5
                                    mRealm.copyToRealmOrUpdate(mHand!!)
                                    mRealm.commitTransaction()

                                    intentShowDown()
                                }
                            }
                        }
                    }

                    "river" -> {
                        if (cardComSet5 == "set") {
                            cardCom5 = cardSuit + cardNumber1 + cardNumber2

                            val handRealmResults = mRealm.where(Hand::class.java).findAll()
                            val hand = mRealm.where(Hand::class.java).equalTo("game_id", game_id).findAll()
                            val handId = hand.max("id")!!.toInt()
                            val handDB = handRealmResults.get(handId)
                            mRealm.beginTransaction()
                            mHand = Hand()
                            handDB!!.table5 = cardCom5
                            mRealm.copyToRealmOrUpdate(mHand!!)
                            mRealm.commitTransaction()

                            intentShowDown()
                        }
                    }
                }
            }
        }
    }

    private fun cardSetting1() {
        when (cardHand1){
            "club1" -> cardHandImageView1.setImageResource(R.drawable.club1)
            "club2" -> cardHandImageView1.setImageResource(R.drawable.club2)
            "club3" -> cardHandImageView1.setImageResource(R.drawable.club3)
            "club4" -> cardHandImageView1.setImageResource(R.drawable.club4)
            "club5" -> cardHandImageView1.setImageResource(R.drawable.club5)
            "club6" -> cardHandImageView1.setImageResource(R.drawable.club6)
            "club7" -> cardHandImageView1.setImageResource(R.drawable.club7)
            "club8" -> cardHandImageView1.setImageResource(R.drawable.club8)
            "club9" -> cardHandImageView1.setImageResource(R.drawable.club9)
            "club10" -> cardHandImageView1.setImageResource(R.drawable.club10)
            "club11" -> cardHandImageView1.setImageResource(R.drawable.club11)
            "club12" -> cardHandImageView1.setImageResource(R.drawable.club12)
            "club13" -> cardHandImageView1.setImageResource(R.drawable.club13)

            "diamond1" -> cardHandImageView1.setImageResource(R.drawable.diamond1)
            "diamond2" -> cardHandImageView1.setImageResource(R.drawable.diamond2)
            "diamond3" -> cardHandImageView1.setImageResource(R.drawable.diamond3)
            "diamond4" -> cardHandImageView1.setImageResource(R.drawable.diamond4)
            "diamond5" -> cardHandImageView1.setImageResource(R.drawable.diamond5)
            "diamond6" -> cardHandImageView1.setImageResource(R.drawable.diamond6)
            "diamond7" -> cardHandImageView1.setImageResource(R.drawable.diamond7)
            "diamond8" -> cardHandImageView1.setImageResource(R.drawable.diamond8)
            "diamond9" -> cardHandImageView1.setImageResource(R.drawable.diamond9)
            "diamond10" -> cardHandImageView1.setImageResource(R.drawable.diamond10)
            "diamond11" -> cardHandImageView1.setImageResource(R.drawable.diamond11)
            "diamond12" -> cardHandImageView1.setImageResource(R.drawable.diamond12)
            "diamond13" -> cardHandImageView1.setImageResource(R.drawable.diamond13)

            "heart1" -> cardHandImageView1.setImageResource(R.drawable.heart1)
            "heart2" -> cardHandImageView1.setImageResource(R.drawable.heart2)
            "heart3" -> cardHandImageView1.setImageResource(R.drawable.heart3)
            "heart4" -> cardHandImageView1.setImageResource(R.drawable.heart4)
            "heart5" -> cardHandImageView1.setImageResource(R.drawable.heart5)
            "heart6" -> cardHandImageView1.setImageResource(R.drawable.heart6)
            "heart7" -> cardHandImageView1.setImageResource(R.drawable.heart7)
            "heart8" -> cardHandImageView1.setImageResource(R.drawable.heart8)
            "heart9" -> cardHandImageView1.setImageResource(R.drawable.heart9)
            "heart10" -> cardHandImageView1.setImageResource(R.drawable.heart10)
            "heart11" -> cardHandImageView1.setImageResource(R.drawable.heart11)
            "heart12" -> cardHandImageView1.setImageResource(R.drawable.heart12)
            "heart13" -> cardHandImageView1.setImageResource(R.drawable.heart13)

            "spade1" -> cardHandImageView1.setImageResource(R.drawable.spade1)
            "spade2" -> cardHandImageView1.setImageResource(R.drawable.spade2)
            "spade3" -> cardHandImageView1.setImageResource(R.drawable.spade3)
            "spade4" -> cardHandImageView1.setImageResource(R.drawable.spade4)
            "spade5" -> cardHandImageView1.setImageResource(R.drawable.spade5)
            "spade6" -> cardHandImageView1.setImageResource(R.drawable.spade6)
            "spade7" -> cardHandImageView1.setImageResource(R.drawable.spade7)
            "spade8" -> cardHandImageView1.setImageResource(R.drawable.spade8)
            "spade9" -> cardHandImageView1.setImageResource(R.drawable.spade9)
            "spade10" -> cardHandImageView1.setImageResource(R.drawable.spade10)
            "spade11" -> cardHandImageView1.setImageResource(R.drawable.spade11)
            "spade12" -> cardHandImageView1.setImageResource(R.drawable.spade12)
            "spade13" -> cardHandImageView1.setImageResource(R.drawable.spade13)
        }
    }

    private fun cardSetting2() {
        when(cardHand2) {
            "club1" -> cardHandImageView2.setImageResource(R.drawable.club1)
            "club2" -> cardHandImageView2.setImageResource(R.drawable.club2)
            "club3" -> cardHandImageView2.setImageResource(R.drawable.club3)
            "club4" -> cardHandImageView2.setImageResource(R.drawable.club4)
            "club5" -> cardHandImageView2.setImageResource(R.drawable.club5)
            "club6" -> cardHandImageView2.setImageResource(R.drawable.club6)
            "club7" -> cardHandImageView2.setImageResource(R.drawable.club7)
            "club8" -> cardHandImageView2.setImageResource(R.drawable.club8)
            "club9" -> cardHandImageView2.setImageResource(R.drawable.club9)
            "club10" -> cardHandImageView2.setImageResource(R.drawable.club10)
            "club11" -> cardHandImageView2.setImageResource(R.drawable.club11)
            "club12" -> cardHandImageView2.setImageResource(R.drawable.club12)
            "club13" -> cardHandImageView2.setImageResource(R.drawable.club13)

            "diamond1" -> cardHandImageView2.setImageResource(R.drawable.diamond1)
            "diamond2" -> cardHandImageView2.setImageResource(R.drawable.diamond2)
            "diamond3" -> cardHandImageView2.setImageResource(R.drawable.diamond3)
            "diamond4" -> cardHandImageView2.setImageResource(R.drawable.diamond4)
            "diamond5" -> cardHandImageView2.setImageResource(R.drawable.diamond5)
            "diamond6" -> cardHandImageView2.setImageResource(R.drawable.diamond6)
            "diamond7" -> cardHandImageView2.setImageResource(R.drawable.diamond7)
            "diamond8" -> cardHandImageView2.setImageResource(R.drawable.diamond8)
            "diamond9" -> cardHandImageView2.setImageResource(R.drawable.diamond9)
            "diamond10" -> cardHandImageView2.setImageResource(R.drawable.diamond10)
            "diamond11" -> cardHandImageView2.setImageResource(R.drawable.diamond11)
            "diamond12" -> cardHandImageView2.setImageResource(R.drawable.diamond12)
            "diamond13" -> cardHandImageView2.setImageResource(R.drawable.diamond13)

            "heart1" -> cardHandImageView2.setImageResource(R.drawable.heart1)
            "heart2" -> cardHandImageView2.setImageResource(R.drawable.heart2)
            "heart3" -> cardHandImageView2.setImageResource(R.drawable.heart3)
            "heart4" -> cardHandImageView2.setImageResource(R.drawable.heart4)
            "heart5" -> cardHandImageView2.setImageResource(R.drawable.heart5)
            "heart6" -> cardHandImageView2.setImageResource(R.drawable.heart6)
            "heart7" -> cardHandImageView2.setImageResource(R.drawable.heart7)
            "heart8" -> cardHandImageView2.setImageResource(R.drawable.heart8)
            "heart9" -> cardHandImageView2.setImageResource(R.drawable.heart9)
            "heart10" -> cardHandImageView2.setImageResource(R.drawable.heart10)
            "heart11" -> cardHandImageView2.setImageResource(R.drawable.heart11)
            "heart12" -> cardHandImageView2.setImageResource(R.drawable.heart12)
            "heart13" -> cardHandImageView2.setImageResource(R.drawable.heart13)

            "spade1" -> cardHandImageView2.setImageResource(R.drawable.spade1)
            "spade2" -> cardHandImageView2.setImageResource(R.drawable.spade2)
            "spade3" -> cardHandImageView2.setImageResource(R.drawable.spade3)
            "spade4" -> cardHandImageView2.setImageResource(R.drawable.spade4)
            "spade5" -> cardHandImageView2.setImageResource(R.drawable.spade5)
            "spade6" -> cardHandImageView2.setImageResource(R.drawable.spade6)
            "spade7" -> cardHandImageView2.setImageResource(R.drawable.spade7)
            "spade8" -> cardHandImageView2.setImageResource(R.drawable.spade8)
            "spade9" -> cardHandImageView2.setImageResource(R.drawable.spade9)
            "spade10" -> cardHandImageView2.setImageResource(R.drawable.spade10)
            "spade11" -> cardHandImageView2.setImageResource(R.drawable.spade11)
            "spade12" -> cardHandImageView2.setImageResource(R.drawable.spade12)
            "spade13" -> cardHandImageView2.setImageResource(R.drawable.spade13)
        }
    }

    private fun cardGetSetting1() {
        when (cardCom1){
            "club1" -> cardComImageView1.setImageResource(R.drawable.club1)
            "club2" -> cardComImageView1.setImageResource(R.drawable.club2)
            "club3" -> cardComImageView1.setImageResource(R.drawable.club3)
            "club4" -> cardComImageView1.setImageResource(R.drawable.club4)
            "club5" -> cardComImageView1.setImageResource(R.drawable.club5)
            "club6" -> cardComImageView1.setImageResource(R.drawable.club6)
            "club7" -> cardComImageView1.setImageResource(R.drawable.club7)
            "club8" -> cardComImageView1.setImageResource(R.drawable.club8)
            "club9" -> cardComImageView1.setImageResource(R.drawable.club9)
            "club10" -> cardComImageView1.setImageResource(R.drawable.club10)
            "club11" -> cardComImageView1.setImageResource(R.drawable.club11)
            "club12" -> cardComImageView1.setImageResource(R.drawable.club12)
            "club13" -> cardComImageView1.setImageResource(R.drawable.club13)

            "diamond1" -> cardComImageView1.setImageResource(R.drawable.diamond1)
            "diamond2" -> cardComImageView1.setImageResource(R.drawable.diamond2)
            "diamond3" -> cardComImageView1.setImageResource(R.drawable.diamond3)
            "diamond4" -> cardComImageView1.setImageResource(R.drawable.diamond4)
            "diamond5" -> cardComImageView1.setImageResource(R.drawable.diamond5)
            "diamond6" -> cardComImageView1.setImageResource(R.drawable.diamond6)
            "diamond7" -> cardComImageView1.setImageResource(R.drawable.diamond7)
            "diamond8" -> cardComImageView1.setImageResource(R.drawable.diamond8)
            "diamond9" -> cardComImageView1.setImageResource(R.drawable.diamond9)
            "diamond10" -> cardComImageView1.setImageResource(R.drawable.diamond10)
            "diamond11" -> cardComImageView1.setImageResource(R.drawable.diamond11)
            "diamond12" -> cardComImageView1.setImageResource(R.drawable.diamond12)
            "diamond13" -> cardComImageView1.setImageResource(R.drawable.diamond13)

            "heart1" -> cardComImageView1.setImageResource(R.drawable.heart1)
            "heart2" -> cardComImageView1.setImageResource(R.drawable.heart2)
            "heart3" -> cardComImageView1.setImageResource(R.drawable.heart3)
            "heart4" -> cardComImageView1.setImageResource(R.drawable.heart4)
            "heart5" -> cardComImageView1.setImageResource(R.drawable.heart5)
            "heart6" -> cardComImageView1.setImageResource(R.drawable.heart6)
            "heart7" -> cardComImageView1.setImageResource(R.drawable.heart7)
            "heart8" -> cardComImageView1.setImageResource(R.drawable.heart8)
            "heart9" -> cardComImageView1.setImageResource(R.drawable.heart9)
            "heart10" -> cardComImageView1.setImageResource(R.drawable.heart10)
            "heart11" -> cardComImageView1.setImageResource(R.drawable.heart11)
            "heart12" -> cardComImageView1.setImageResource(R.drawable.heart12)
            "heart13" -> cardComImageView1.setImageResource(R.drawable.heart13)

            "spade1" -> cardComImageView1.setImageResource(R.drawable.spade1)
            "spade2" -> cardComImageView1.setImageResource(R.drawable.spade2)
            "spade3" -> cardComImageView1.setImageResource(R.drawable.spade3)
            "spade4" -> cardComImageView1.setImageResource(R.drawable.spade4)
            "spade5" -> cardComImageView1.setImageResource(R.drawable.spade5)
            "spade6" -> cardComImageView1.setImageResource(R.drawable.spade6)
            "spade7" -> cardComImageView1.setImageResource(R.drawable.spade7)
            "spade8" -> cardComImageView1.setImageResource(R.drawable.spade8)
            "spade9" -> cardComImageView1.setImageResource(R.drawable.spade9)
            "spade10" -> cardComImageView1.setImageResource(R.drawable.spade10)
            "spade11" -> cardComImageView1.setImageResource(R.drawable.spade11)
            "spade12" -> cardComImageView1.setImageResource(R.drawable.spade12)
            "spade13" -> cardComImageView1.setImageResource(R.drawable.spade13)
        }
        cardComSet1 = "set"
    }

    private fun cardGetSetting2() {
        when (cardCom2){
            "club1" -> cardComImageView2.setImageResource(R.drawable.club1)
            "club2" -> cardComImageView2.setImageResource(R.drawable.club2)
            "club3" -> cardComImageView2.setImageResource(R.drawable.club3)
            "club4" -> cardComImageView2.setImageResource(R.drawable.club4)
            "club5" -> cardComImageView2.setImageResource(R.drawable.club5)
            "club6" -> cardComImageView2.setImageResource(R.drawable.club6)
            "club7" -> cardComImageView2.setImageResource(R.drawable.club7)
            "club8" -> cardComImageView2.setImageResource(R.drawable.club8)
            "club9" -> cardComImageView2.setImageResource(R.drawable.club9)
            "club10" -> cardComImageView2.setImageResource(R.drawable.club10)
            "club11" -> cardComImageView2.setImageResource(R.drawable.club11)
            "club12" -> cardComImageView2.setImageResource(R.drawable.club12)
            "club13" -> cardComImageView2.setImageResource(R.drawable.club13)

            "diamond1" -> cardComImageView2.setImageResource(R.drawable.diamond1)
            "diamond2" -> cardComImageView2.setImageResource(R.drawable.diamond2)
            "diamond3" -> cardComImageView2.setImageResource(R.drawable.diamond3)
            "diamond4" -> cardComImageView2.setImageResource(R.drawable.diamond4)
            "diamond5" -> cardComImageView2.setImageResource(R.drawable.diamond5)
            "diamond6" -> cardComImageView2.setImageResource(R.drawable.diamond6)
            "diamond7" -> cardComImageView2.setImageResource(R.drawable.diamond7)
            "diamond8" -> cardComImageView2.setImageResource(R.drawable.diamond8)
            "diamond9" -> cardComImageView2.setImageResource(R.drawable.diamond9)
            "diamond10" -> cardComImageView2.setImageResource(R.drawable.diamond10)
            "diamond11" -> cardComImageView2.setImageResource(R.drawable.diamond11)
            "diamond12" -> cardComImageView2.setImageResource(R.drawable.diamond12)
            "diamond13" -> cardComImageView2.setImageResource(R.drawable.diamond13)

            "heart1" -> cardComImageView2.setImageResource(R.drawable.heart1)
            "heart2" -> cardComImageView2.setImageResource(R.drawable.heart2)
            "heart3" -> cardComImageView2.setImageResource(R.drawable.heart3)
            "heart4" -> cardComImageView2.setImageResource(R.drawable.heart4)
            "heart5" -> cardComImageView2.setImageResource(R.drawable.heart5)
            "heart6" -> cardComImageView2.setImageResource(R.drawable.heart6)
            "heart7" -> cardComImageView2.setImageResource(R.drawable.heart7)
            "heart8" -> cardComImageView2.setImageResource(R.drawable.heart8)
            "heart9" -> cardComImageView2.setImageResource(R.drawable.heart9)
            "heart10" -> cardComImageView2.setImageResource(R.drawable.heart10)
            "heart11" -> cardComImageView2.setImageResource(R.drawable.heart11)
            "heart12" -> cardComImageView2.setImageResource(R.drawable.heart12)
            "heart13" -> cardComImageView2.setImageResource(R.drawable.heart13)

            "spade1" -> cardComImageView2.setImageResource(R.drawable.spade1)
            "spade2" -> cardComImageView2.setImageResource(R.drawable.spade2)
            "spade3" -> cardComImageView2.setImageResource(R.drawable.spade3)
            "spade4" -> cardComImageView2.setImageResource(R.drawable.spade4)
            "spade5" -> cardComImageView2.setImageResource(R.drawable.spade5)
            "spade6" -> cardComImageView2.setImageResource(R.drawable.spade6)
            "spade7" -> cardComImageView2.setImageResource(R.drawable.spade7)
            "spade8" -> cardComImageView2.setImageResource(R.drawable.spade8)
            "spade9" -> cardComImageView2.setImageResource(R.drawable.spade9)
            "spade10" -> cardComImageView2.setImageResource(R.drawable.spade10)
            "spade11" -> cardComImageView2.setImageResource(R.drawable.spade11)
            "spade12" -> cardComImageView2.setImageResource(R.drawable.spade12)
            "spade13" -> cardComImageView2.setImageResource(R.drawable.spade13)
        }
        cardComSet2 = "set"
    }

    private fun cardGetSetting3() {
        when (cardCom3){
            "club1" -> cardComImageView3.setImageResource(R.drawable.club1)
            "club2" -> cardComImageView3.setImageResource(R.drawable.club2)
            "club3" -> cardComImageView3.setImageResource(R.drawable.club3)
            "club4" -> cardComImageView3.setImageResource(R.drawable.club4)
            "club5" -> cardComImageView3.setImageResource(R.drawable.club5)
            "club6" -> cardComImageView3.setImageResource(R.drawable.club6)
            "club7" -> cardComImageView3.setImageResource(R.drawable.club7)
            "club8" -> cardComImageView3.setImageResource(R.drawable.club8)
            "club9" -> cardComImageView3.setImageResource(R.drawable.club9)
            "club10" -> cardComImageView3.setImageResource(R.drawable.club10)
            "club11" -> cardComImageView3.setImageResource(R.drawable.club11)
            "club12" -> cardComImageView3.setImageResource(R.drawable.club12)
            "club13" -> cardComImageView3.setImageResource(R.drawable.club13)

            "diamond1" -> cardComImageView3.setImageResource(R.drawable.diamond1)
            "diamond2" -> cardComImageView3.setImageResource(R.drawable.diamond2)
            "diamond3" -> cardComImageView3.setImageResource(R.drawable.diamond3)
            "diamond4" -> cardComImageView3.setImageResource(R.drawable.diamond4)
            "diamond5" -> cardComImageView3.setImageResource(R.drawable.diamond5)
            "diamond6" -> cardComImageView3.setImageResource(R.drawable.diamond6)
            "diamond7" -> cardComImageView3.setImageResource(R.drawable.diamond7)
            "diamond8" -> cardComImageView3.setImageResource(R.drawable.diamond8)
            "diamond9" -> cardComImageView3.setImageResource(R.drawable.diamond9)
            "diamond10" -> cardComImageView3.setImageResource(R.drawable.diamond10)
            "diamond11" -> cardComImageView3.setImageResource(R.drawable.diamond11)
            "diamond12" -> cardComImageView3.setImageResource(R.drawable.diamond12)
            "diamond13" -> cardComImageView3.setImageResource(R.drawable.diamond13)

            "heart1" -> cardComImageView3.setImageResource(R.drawable.heart1)
            "heart2" -> cardComImageView3.setImageResource(R.drawable.heart2)
            "heart3" -> cardComImageView3.setImageResource(R.drawable.heart3)
            "heart4" -> cardComImageView3.setImageResource(R.drawable.heart4)
            "heart5" -> cardComImageView3.setImageResource(R.drawable.heart5)
            "heart6" -> cardComImageView3.setImageResource(R.drawable.heart6)
            "heart7" -> cardComImageView3.setImageResource(R.drawable.heart7)
            "heart8" -> cardComImageView3.setImageResource(R.drawable.heart8)
            "heart9" -> cardComImageView3.setImageResource(R.drawable.heart9)
            "heart10" -> cardComImageView3.setImageResource(R.drawable.heart10)
            "heart11" -> cardComImageView3.setImageResource(R.drawable.heart11)
            "heart12" -> cardComImageView3.setImageResource(R.drawable.heart12)
            "heart13" -> cardComImageView3.setImageResource(R.drawable.heart13)

            "spade1" -> cardComImageView3.setImageResource(R.drawable.spade1)
            "spade2" -> cardComImageView3.setImageResource(R.drawable.spade2)
            "spade3" -> cardComImageView3.setImageResource(R.drawable.spade3)
            "spade4" -> cardComImageView3.setImageResource(R.drawable.spade4)
            "spade5" -> cardComImageView3.setImageResource(R.drawable.spade5)
            "spade6" -> cardComImageView3.setImageResource(R.drawable.spade6)
            "spade7" -> cardComImageView3.setImageResource(R.drawable.spade7)
            "spade8" -> cardComImageView3.setImageResource(R.drawable.spade8)
            "spade9" -> cardComImageView3.setImageResource(R.drawable.spade9)
            "spade10" -> cardComImageView3.setImageResource(R.drawable.spade10)
            "spade11" -> cardComImageView3.setImageResource(R.drawable.spade11)
            "spade12" -> cardComImageView3.setImageResource(R.drawable.spade12)
            "spade13" -> cardComImageView3.setImageResource(R.drawable.spade13)
        }
        cardComSet3 = "set"
    }

    private fun cardGetSetting4() {
        when (cardCom4){
            "club1" -> cardComImageView4.setImageResource(R.drawable.club1)
            "club2" -> cardComImageView4.setImageResource(R.drawable.club2)
            "club3" -> cardComImageView4.setImageResource(R.drawable.club3)
            "club4" -> cardComImageView4.setImageResource(R.drawable.club4)
            "club5" -> cardComImageView4.setImageResource(R.drawable.club5)
            "club6" -> cardComImageView4.setImageResource(R.drawable.club6)
            "club7" -> cardComImageView4.setImageResource(R.drawable.club7)
            "club8" -> cardComImageView4.setImageResource(R.drawable.club8)
            "club9" -> cardComImageView4.setImageResource(R.drawable.club9)
            "club10" -> cardComImageView4.setImageResource(R.drawable.club10)
            "club11" -> cardComImageView4.setImageResource(R.drawable.club11)
            "club12" -> cardComImageView4.setImageResource(R.drawable.club12)
            "club13" -> cardComImageView4.setImageResource(R.drawable.club13)

            "diamond1" -> cardComImageView4.setImageResource(R.drawable.diamond1)
            "diamond2" -> cardComImageView4.setImageResource(R.drawable.diamond2)
            "diamond3" -> cardComImageView4.setImageResource(R.drawable.diamond3)
            "diamond4" -> cardComImageView4.setImageResource(R.drawable.diamond4)
            "diamond5" -> cardComImageView4.setImageResource(R.drawable.diamond5)
            "diamond6" -> cardComImageView4.setImageResource(R.drawable.diamond6)
            "diamond7" -> cardComImageView4.setImageResource(R.drawable.diamond7)
            "diamond8" -> cardComImageView4.setImageResource(R.drawable.diamond8)
            "diamond9" -> cardComImageView4.setImageResource(R.drawable.diamond9)
            "diamond10" -> cardComImageView4.setImageResource(R.drawable.diamond10)
            "diamond11" -> cardComImageView4.setImageResource(R.drawable.diamond11)
            "diamond12" -> cardComImageView4.setImageResource(R.drawable.diamond12)
            "diamond13" -> cardComImageView4.setImageResource(R.drawable.diamond13)

            "heart1" -> cardComImageView4.setImageResource(R.drawable.heart1)
            "heart2" -> cardComImageView4.setImageResource(R.drawable.heart2)
            "heart3" -> cardComImageView4.setImageResource(R.drawable.heart3)
            "heart4" -> cardComImageView4.setImageResource(R.drawable.heart4)
            "heart5" -> cardComImageView4.setImageResource(R.drawable.heart5)
            "heart6" -> cardComImageView4.setImageResource(R.drawable.heart6)
            "heart7" -> cardComImageView4.setImageResource(R.drawable.heart7)
            "heart8" -> cardComImageView4.setImageResource(R.drawable.heart8)
            "heart9" -> cardComImageView4.setImageResource(R.drawable.heart9)
            "heart10" -> cardComImageView4.setImageResource(R.drawable.heart10)
            "heart11" -> cardComImageView4.setImageResource(R.drawable.heart11)
            "heart12" -> cardComImageView4.setImageResource(R.drawable.heart12)
            "heart13" -> cardComImageView4.setImageResource(R.drawable.heart13)

            "spade1" -> cardComImageView4.setImageResource(R.drawable.spade1)
            "spade2" -> cardComImageView4.setImageResource(R.drawable.spade2)
            "spade3" -> cardComImageView4.setImageResource(R.drawable.spade3)
            "spade4" -> cardComImageView4.setImageResource(R.drawable.spade4)
            "spade5" -> cardComImageView4.setImageResource(R.drawable.spade5)
            "spade6" -> cardComImageView4.setImageResource(R.drawable.spade6)
            "spade7" -> cardComImageView4.setImageResource(R.drawable.spade7)
            "spade8" -> cardComImageView4.setImageResource(R.drawable.spade8)
            "spade9" -> cardComImageView4.setImageResource(R.drawable.spade9)
            "spade10" -> cardComImageView4.setImageResource(R.drawable.spade10)
            "spade11" -> cardComImageView4.setImageResource(R.drawable.spade11)
            "spade12" -> cardComImageView4.setImageResource(R.drawable.spade12)
            "spade13" -> cardComImageView4.setImageResource(R.drawable.spade13)
        }
        cardComSet4 = "set"
    }

    private fun cardGetSetting5() {
        when (cardCom5){
            "club1" -> cardComImageView5.setImageResource(R.drawable.club1)
            "club2" -> cardComImageView5.setImageResource(R.drawable.club2)
            "club3" -> cardComImageView5.setImageResource(R.drawable.club3)
            "club4" -> cardComImageView5.setImageResource(R.drawable.club4)
            "club5" -> cardComImageView5.setImageResource(R.drawable.club5)
            "club6" -> cardComImageView5.setImageResource(R.drawable.club6)
            "club7" -> cardComImageView5.setImageResource(R.drawable.club7)
            "club8" -> cardComImageView5.setImageResource(R.drawable.club8)
            "club9" -> cardComImageView5.setImageResource(R.drawable.club9)
            "club10" -> cardComImageView5.setImageResource(R.drawable.club10)
            "club11" -> cardComImageView5.setImageResource(R.drawable.club11)
            "club12" -> cardComImageView5.setImageResource(R.drawable.club12)
            "club13" -> cardComImageView5.setImageResource(R.drawable.club13)

            "diamond1" -> cardComImageView5.setImageResource(R.drawable.diamond1)
            "diamond2" -> cardComImageView5.setImageResource(R.drawable.diamond2)
            "diamond3" -> cardComImageView5.setImageResource(R.drawable.diamond3)
            "diamond4" -> cardComImageView5.setImageResource(R.drawable.diamond4)
            "diamond5" -> cardComImageView5.setImageResource(R.drawable.diamond5)
            "diamond6" -> cardComImageView5.setImageResource(R.drawable.diamond6)
            "diamond7" -> cardComImageView5.setImageResource(R.drawable.diamond7)
            "diamond8" -> cardComImageView5.setImageResource(R.drawable.diamond8)
            "diamond9" -> cardComImageView5.setImageResource(R.drawable.diamond9)
            "diamond10" -> cardComImageView5.setImageResource(R.drawable.diamond10)
            "diamond11" -> cardComImageView5.setImageResource(R.drawable.diamond11)
            "diamond12" -> cardComImageView5.setImageResource(R.drawable.diamond12)
            "diamond13" -> cardComImageView5.setImageResource(R.drawable.diamond13)

            "heart1" -> cardComImageView5.setImageResource(R.drawable.heart1)
            "heart2" -> cardComImageView5.setImageResource(R.drawable.heart2)
            "heart3" -> cardComImageView5.setImageResource(R.drawable.heart3)
            "heart4" -> cardComImageView5.setImageResource(R.drawable.heart4)
            "heart5" -> cardComImageView5.setImageResource(R.drawable.heart5)
            "heart6" -> cardComImageView5.setImageResource(R.drawable.heart6)
            "heart7" -> cardComImageView5.setImageResource(R.drawable.heart7)
            "heart8" -> cardComImageView5.setImageResource(R.drawable.heart8)
            "heart9" -> cardComImageView5.setImageResource(R.drawable.heart9)
            "heart10" -> cardComImageView5.setImageResource(R.drawable.heart10)
            "heart11" -> cardComImageView5.setImageResource(R.drawable.heart11)
            "heart12" -> cardComImageView5.setImageResource(R.drawable.heart12)
            "heart13" -> cardComImageView5.setImageResource(R.drawable.heart13)

            "spade1" -> cardComImageView5.setImageResource(R.drawable.spade1)
            "spade2" -> cardComImageView5.setImageResource(R.drawable.spade2)
            "spade3" -> cardComImageView5.setImageResource(R.drawable.spade3)
            "spade4" -> cardComImageView5.setImageResource(R.drawable.spade4)
            "spade5" -> cardComImageView5.setImageResource(R.drawable.spade5)
            "spade6" -> cardComImageView5.setImageResource(R.drawable.spade6)
            "spade7" -> cardComImageView5.setImageResource(R.drawable.spade7)
            "spade8" -> cardComImageView5.setImageResource(R.drawable.spade8)
            "spade9" -> cardComImageView5.setImageResource(R.drawable.spade9)
            "spade10" -> cardComImageView5.setImageResource(R.drawable.spade10)
            "spade11" -> cardComImageView5.setImageResource(R.drawable.spade11)
            "spade12" -> cardComImageView5.setImageResource(R.drawable.spade12)
            "spade13" -> cardComImageView5.setImageResource(R.drawable.spade13)
        }
        cardComSet5 = "set"
    }

    private fun cardComSetting1() {
        when (cardSuit + cardNumber1 + cardNumber2){
            "club1" -> cardComImageView1.setImageResource(R.drawable.club1)
            "club2" -> cardComImageView1.setImageResource(R.drawable.club2)
            "club3" -> cardComImageView1.setImageResource(R.drawable.club3)
            "club4" -> cardComImageView1.setImageResource(R.drawable.club4)
            "club5" -> cardComImageView1.setImageResource(R.drawable.club5)
            "club6" -> cardComImageView1.setImageResource(R.drawable.club6)
            "club7" -> cardComImageView1.setImageResource(R.drawable.club7)
            "club8" -> cardComImageView1.setImageResource(R.drawable.club8)
            "club9" -> cardComImageView1.setImageResource(R.drawable.club9)
            "club10" -> cardComImageView1.setImageResource(R.drawable.club10)
            "club11" -> cardComImageView1.setImageResource(R.drawable.club11)
            "club12" -> cardComImageView1.setImageResource(R.drawable.club12)
            "club13" -> cardComImageView1.setImageResource(R.drawable.club13)

            "diamond1" -> cardComImageView1.setImageResource(R.drawable.diamond1)
            "diamond2" -> cardComImageView1.setImageResource(R.drawable.diamond2)
            "diamond3" -> cardComImageView1.setImageResource(R.drawable.diamond3)
            "diamond4" -> cardComImageView1.setImageResource(R.drawable.diamond4)
            "diamond5" -> cardComImageView1.setImageResource(R.drawable.diamond5)
            "diamond6" -> cardComImageView1.setImageResource(R.drawable.diamond6)
            "diamond7" -> cardComImageView1.setImageResource(R.drawable.diamond7)
            "diamond8" -> cardComImageView1.setImageResource(R.drawable.diamond8)
            "diamond9" -> cardComImageView1.setImageResource(R.drawable.diamond9)
            "diamond10" -> cardComImageView1.setImageResource(R.drawable.diamond10)
            "diamond11" -> cardComImageView1.setImageResource(R.drawable.diamond11)
            "diamond12" -> cardComImageView1.setImageResource(R.drawable.diamond12)
            "diamond13" -> cardComImageView1.setImageResource(R.drawable.diamond13)

            "heart1" -> cardComImageView1.setImageResource(R.drawable.heart1)
            "heart2" -> cardComImageView1.setImageResource(R.drawable.heart2)
            "heart3" -> cardComImageView1.setImageResource(R.drawable.heart3)
            "heart4" -> cardComImageView1.setImageResource(R.drawable.heart4)
            "heart5" -> cardComImageView1.setImageResource(R.drawable.heart5)
            "heart6" -> cardComImageView1.setImageResource(R.drawable.heart6)
            "heart7" -> cardComImageView1.setImageResource(R.drawable.heart7)
            "heart8" -> cardComImageView1.setImageResource(R.drawable.heart8)
            "heart9" -> cardComImageView1.setImageResource(R.drawable.heart9)
            "heart10" -> cardComImageView1.setImageResource(R.drawable.heart10)
            "heart11" -> cardComImageView1.setImageResource(R.drawable.heart11)
            "heart12" -> cardComImageView1.setImageResource(R.drawable.heart12)
            "heart13" -> cardComImageView1.setImageResource(R.drawable.heart13)

            "spade1" -> cardComImageView1.setImageResource(R.drawable.spade1)
            "spade2" -> cardComImageView1.setImageResource(R.drawable.spade2)
            "spade3" -> cardComImageView1.setImageResource(R.drawable.spade3)
            "spade4" -> cardComImageView1.setImageResource(R.drawable.spade4)
            "spade5" -> cardComImageView1.setImageResource(R.drawable.spade5)
            "spade6" -> cardComImageView1.setImageResource(R.drawable.spade6)
            "spade7" -> cardComImageView1.setImageResource(R.drawable.spade7)
            "spade8" -> cardComImageView1.setImageResource(R.drawable.spade8)
            "spade9" -> cardComImageView1.setImageResource(R.drawable.spade9)
            "spade10" -> cardComImageView1.setImageResource(R.drawable.spade10)
            "spade11" -> cardComImageView1.setImageResource(R.drawable.spade11)
            "spade12" -> cardComImageView1.setImageResource(R.drawable.spade12)
            "spade13" -> cardComImageView1.setImageResource(R.drawable.spade13)
        }
        cardComSet1 = "set"
    }

    private fun cardComSetting2() {
        when (cardSuit + cardNumber1 + cardNumber2){
            "club1" -> cardComImageView2.setImageResource(R.drawable.club1)
            "club2" -> cardComImageView2.setImageResource(R.drawable.club2)
            "club3" -> cardComImageView2.setImageResource(R.drawable.club3)
            "club4" -> cardComImageView2.setImageResource(R.drawable.club4)
            "club5" -> cardComImageView2.setImageResource(R.drawable.club5)
            "club6" -> cardComImageView2.setImageResource(R.drawable.club6)
            "club7" -> cardComImageView2.setImageResource(R.drawable.club7)
            "club8" -> cardComImageView2.setImageResource(R.drawable.club8)
            "club9" -> cardComImageView2.setImageResource(R.drawable.club9)
            "club10" -> cardComImageView2.setImageResource(R.drawable.club10)
            "club11" -> cardComImageView2.setImageResource(R.drawable.club11)
            "club12" -> cardComImageView2.setImageResource(R.drawable.club12)
            "club13" -> cardComImageView2.setImageResource(R.drawable.club13)

            "diamond1" -> cardComImageView2.setImageResource(R.drawable.diamond1)
            "diamond2" -> cardComImageView2.setImageResource(R.drawable.diamond2)
            "diamond3" -> cardComImageView2.setImageResource(R.drawable.diamond3)
            "diamond4" -> cardComImageView2.setImageResource(R.drawable.diamond4)
            "diamond5" -> cardComImageView2.setImageResource(R.drawable.diamond5)
            "diamond6" -> cardComImageView2.setImageResource(R.drawable.diamond6)
            "diamond7" -> cardComImageView2.setImageResource(R.drawable.diamond7)
            "diamond8" -> cardComImageView2.setImageResource(R.drawable.diamond8)
            "diamond9" -> cardComImageView2.setImageResource(R.drawable.diamond9)
            "diamond10" -> cardComImageView2.setImageResource(R.drawable.diamond10)
            "diamond11" -> cardComImageView2.setImageResource(R.drawable.diamond11)
            "diamond12" -> cardComImageView2.setImageResource(R.drawable.diamond12)
            "diamond13" -> cardComImageView2.setImageResource(R.drawable.diamond13)

            "heart1" -> cardComImageView2.setImageResource(R.drawable.heart1)
            "heart2" -> cardComImageView2.setImageResource(R.drawable.heart2)
            "heart3" -> cardComImageView2.setImageResource(R.drawable.heart3)
            "heart4" -> cardComImageView2.setImageResource(R.drawable.heart4)
            "heart5" -> cardComImageView2.setImageResource(R.drawable.heart5)
            "heart6" -> cardComImageView2.setImageResource(R.drawable.heart6)
            "heart7" -> cardComImageView2.setImageResource(R.drawable.heart7)
            "heart8" -> cardComImageView2.setImageResource(R.drawable.heart8)
            "heart9" -> cardComImageView2.setImageResource(R.drawable.heart9)
            "heart10" -> cardComImageView2.setImageResource(R.drawable.heart10)
            "heart11" -> cardComImageView2.setImageResource(R.drawable.heart11)
            "heart12" -> cardComImageView2.setImageResource(R.drawable.heart12)
            "heart13" -> cardComImageView2.setImageResource(R.drawable.heart13)

            "spade1" -> cardComImageView2.setImageResource(R.drawable.spade1)
            "spade2" -> cardComImageView2.setImageResource(R.drawable.spade2)
            "spade3" -> cardComImageView2.setImageResource(R.drawable.spade3)
            "spade4" -> cardComImageView2.setImageResource(R.drawable.spade4)
            "spade5" -> cardComImageView2.setImageResource(R.drawable.spade5)
            "spade6" -> cardComImageView2.setImageResource(R.drawable.spade6)
            "spade7" -> cardComImageView2.setImageResource(R.drawable.spade7)
            "spade8" -> cardComImageView2.setImageResource(R.drawable.spade8)
            "spade9" -> cardComImageView2.setImageResource(R.drawable.spade9)
            "spade10" -> cardComImageView2.setImageResource(R.drawable.spade10)
            "spade11" -> cardComImageView2.setImageResource(R.drawable.spade11)
            "spade12" -> cardComImageView2.setImageResource(R.drawable.spade12)
            "spade13" -> cardComImageView2.setImageResource(R.drawable.spade13)
        }
        cardComSet2 = "set"
    }

    private fun cardComSetting3() {
        when (cardSuit + cardNumber1 + cardNumber2){
            "club1" -> cardComImageView3.setImageResource(R.drawable.club1)
            "club2" -> cardComImageView3.setImageResource(R.drawable.club2)
            "club3" -> cardComImageView3.setImageResource(R.drawable.club3)
            "club4" -> cardComImageView3.setImageResource(R.drawable.club4)
            "club5" -> cardComImageView3.setImageResource(R.drawable.club5)
            "club6" -> cardComImageView3.setImageResource(R.drawable.club6)
            "club7" -> cardComImageView3.setImageResource(R.drawable.club7)
            "club8" -> cardComImageView3.setImageResource(R.drawable.club8)
            "club9" -> cardComImageView3.setImageResource(R.drawable.club9)
            "club10" -> cardComImageView3.setImageResource(R.drawable.club10)
            "club11" -> cardComImageView3.setImageResource(R.drawable.club11)
            "club12" -> cardComImageView3.setImageResource(R.drawable.club12)
            "club13" -> cardComImageView3.setImageResource(R.drawable.club13)

            "diamond1" -> cardComImageView3.setImageResource(R.drawable.diamond1)
            "diamond2" -> cardComImageView3.setImageResource(R.drawable.diamond2)
            "diamond3" -> cardComImageView3.setImageResource(R.drawable.diamond3)
            "diamond4" -> cardComImageView3.setImageResource(R.drawable.diamond4)
            "diamond5" -> cardComImageView3.setImageResource(R.drawable.diamond5)
            "diamond6" -> cardComImageView3.setImageResource(R.drawable.diamond6)
            "diamond7" -> cardComImageView3.setImageResource(R.drawable.diamond7)
            "diamond8" -> cardComImageView3.setImageResource(R.drawable.diamond8)
            "diamond9" -> cardComImageView3.setImageResource(R.drawable.diamond9)
            "diamond10" -> cardComImageView3.setImageResource(R.drawable.diamond10)
            "diamond11" -> cardComImageView3.setImageResource(R.drawable.diamond11)
            "diamond12" -> cardComImageView3.setImageResource(R.drawable.diamond12)
            "diamond13" -> cardComImageView3.setImageResource(R.drawable.diamond13)

            "heart1" -> cardComImageView3.setImageResource(R.drawable.heart1)
            "heart2" -> cardComImageView3.setImageResource(R.drawable.heart2)
            "heart3" -> cardComImageView3.setImageResource(R.drawable.heart3)
            "heart4" -> cardComImageView3.setImageResource(R.drawable.heart4)
            "heart5" -> cardComImageView3.setImageResource(R.drawable.heart5)
            "heart6" -> cardComImageView3.setImageResource(R.drawable.heart6)
            "heart7" -> cardComImageView3.setImageResource(R.drawable.heart7)
            "heart8" -> cardComImageView3.setImageResource(R.drawable.heart8)
            "heart9" -> cardComImageView3.setImageResource(R.drawable.heart9)
            "heart10" -> cardComImageView3.setImageResource(R.drawable.heart10)
            "heart11" -> cardComImageView3.setImageResource(R.drawable.heart11)
            "heart12" -> cardComImageView3.setImageResource(R.drawable.heart12)
            "heart13" -> cardComImageView3.setImageResource(R.drawable.heart13)

            "spade1" -> cardComImageView3.setImageResource(R.drawable.spade1)
            "spade2" -> cardComImageView3.setImageResource(R.drawable.spade2)
            "spade3" -> cardComImageView3.setImageResource(R.drawable.spade3)
            "spade4" -> cardComImageView3.setImageResource(R.drawable.spade4)
            "spade5" -> cardComImageView3.setImageResource(R.drawable.spade5)
            "spade6" -> cardComImageView3.setImageResource(R.drawable.spade6)
            "spade7" -> cardComImageView3.setImageResource(R.drawable.spade7)
            "spade8" -> cardComImageView3.setImageResource(R.drawable.spade8)
            "spade9" -> cardComImageView3.setImageResource(R.drawable.spade9)
            "spade10" -> cardComImageView3.setImageResource(R.drawable.spade10)
            "spade11" -> cardComImageView3.setImageResource(R.drawable.spade11)
            "spade12" -> cardComImageView3.setImageResource(R.drawable.spade12)
            "spade13" -> cardComImageView3.setImageResource(R.drawable.spade13)
        }
        cardComSet3 = "set"
    }

    private fun cardComSetting4() {
        when (cardSuit + cardNumber1 + cardNumber2){
            "club1" -> cardComImageView4.setImageResource(R.drawable.club1)
            "club2" -> cardComImageView4.setImageResource(R.drawable.club2)
            "club3" -> cardComImageView4.setImageResource(R.drawable.club3)
            "club4" -> cardComImageView4.setImageResource(R.drawable.club4)
            "club5" -> cardComImageView4.setImageResource(R.drawable.club5)
            "club6" -> cardComImageView4.setImageResource(R.drawable.club6)
            "club7" -> cardComImageView4.setImageResource(R.drawable.club7)
            "club8" -> cardComImageView4.setImageResource(R.drawable.club8)
            "club9" -> cardComImageView4.setImageResource(R.drawable.club9)
            "club10" -> cardComImageView4.setImageResource(R.drawable.club10)
            "club11" -> cardComImageView4.setImageResource(R.drawable.club11)
            "club12" -> cardComImageView4.setImageResource(R.drawable.club12)
            "club13" -> cardComImageView4.setImageResource(R.drawable.club13)

            "diamond1" -> cardComImageView4.setImageResource(R.drawable.diamond1)
            "diamond2" -> cardComImageView4.setImageResource(R.drawable.diamond2)
            "diamond3" -> cardComImageView4.setImageResource(R.drawable.diamond3)
            "diamond4" -> cardComImageView4.setImageResource(R.drawable.diamond4)
            "diamond5" -> cardComImageView4.setImageResource(R.drawable.diamond5)
            "diamond6" -> cardComImageView4.setImageResource(R.drawable.diamond6)
            "diamond7" -> cardComImageView4.setImageResource(R.drawable.diamond7)
            "diamond8" -> cardComImageView4.setImageResource(R.drawable.diamond8)
            "diamond9" -> cardComImageView4.setImageResource(R.drawable.diamond9)
            "diamond10" -> cardComImageView4.setImageResource(R.drawable.diamond10)
            "diamond11" -> cardComImageView4.setImageResource(R.drawable.diamond11)
            "diamond12" -> cardComImageView4.setImageResource(R.drawable.diamond12)
            "diamond13" -> cardComImageView4.setImageResource(R.drawable.diamond13)

            "heart1" -> cardComImageView4.setImageResource(R.drawable.heart1)
            "heart2" -> cardComImageView4.setImageResource(R.drawable.heart2)
            "heart3" -> cardComImageView4.setImageResource(R.drawable.heart3)
            "heart4" -> cardComImageView4.setImageResource(R.drawable.heart4)
            "heart5" -> cardComImageView4.setImageResource(R.drawable.heart5)
            "heart6" -> cardComImageView4.setImageResource(R.drawable.heart6)
            "heart7" -> cardComImageView4.setImageResource(R.drawable.heart7)
            "heart8" -> cardComImageView4.setImageResource(R.drawable.heart8)
            "heart9" -> cardComImageView4.setImageResource(R.drawable.heart9)
            "heart10" -> cardComImageView4.setImageResource(R.drawable.heart10)
            "heart11" -> cardComImageView4.setImageResource(R.drawable.heart11)
            "heart12" -> cardComImageView4.setImageResource(R.drawable.heart12)
            "heart13" -> cardComImageView4.setImageResource(R.drawable.heart13)

            "spade1" -> cardComImageView4.setImageResource(R.drawable.spade1)
            "spade2" -> cardComImageView4.setImageResource(R.drawable.spade2)
            "spade3" -> cardComImageView4.setImageResource(R.drawable.spade3)
            "spade4" -> cardComImageView4.setImageResource(R.drawable.spade4)
            "spade5" -> cardComImageView4.setImageResource(R.drawable.spade5)
            "spade6" -> cardComImageView4.setImageResource(R.drawable.spade6)
            "spade7" -> cardComImageView4.setImageResource(R.drawable.spade7)
            "spade8" -> cardComImageView4.setImageResource(R.drawable.spade8)
            "spade9" -> cardComImageView4.setImageResource(R.drawable.spade9)
            "spade10" -> cardComImageView4.setImageResource(R.drawable.spade10)
            "spade11" -> cardComImageView4.setImageResource(R.drawable.spade11)
            "spade12" -> cardComImageView4.setImageResource(R.drawable.spade12)
            "spade13" -> cardComImageView4.setImageResource(R.drawable.spade13)
        }
        cardComSet4 = "set"
    }

    private fun cardComSetting5() {
        when (cardSuit + cardNumber1 + cardNumber2){
            "club1" -> cardComImageView5.setImageResource(R.drawable.club1)
            "club2" -> cardComImageView5.setImageResource(R.drawable.club2)
            "club3" -> cardComImageView5.setImageResource(R.drawable.club3)
            "club4" -> cardComImageView5.setImageResource(R.drawable.club4)
            "club5" -> cardComImageView5.setImageResource(R.drawable.club5)
            "club6" -> cardComImageView5.setImageResource(R.drawable.club6)
            "club7" -> cardComImageView5.setImageResource(R.drawable.club7)
            "club8" -> cardComImageView5.setImageResource(R.drawable.club8)
            "club9" -> cardComImageView5.setImageResource(R.drawable.club9)
            "club10" -> cardComImageView5.setImageResource(R.drawable.club10)
            "club11" -> cardComImageView5.setImageResource(R.drawable.club11)
            "club12" -> cardComImageView5.setImageResource(R.drawable.club12)
            "club13" -> cardComImageView5.setImageResource(R.drawable.club13)

            "diamond1" -> cardComImageView5.setImageResource(R.drawable.diamond1)
            "diamond2" -> cardComImageView5.setImageResource(R.drawable.diamond2)
            "diamond3" -> cardComImageView5.setImageResource(R.drawable.diamond3)
            "diamond4" -> cardComImageView5.setImageResource(R.drawable.diamond4)
            "diamond5" -> cardComImageView5.setImageResource(R.drawable.diamond5)
            "diamond6" -> cardComImageView5.setImageResource(R.drawable.diamond6)
            "diamond7" -> cardComImageView5.setImageResource(R.drawable.diamond7)
            "diamond8" -> cardComImageView5.setImageResource(R.drawable.diamond8)
            "diamond9" -> cardComImageView5.setImageResource(R.drawable.diamond9)
            "diamond10" -> cardComImageView5.setImageResource(R.drawable.diamond10)
            "diamond11" -> cardComImageView5.setImageResource(R.drawable.diamond11)
            "diamond12" -> cardComImageView5.setImageResource(R.drawable.diamond12)
            "diamond13" -> cardComImageView5.setImageResource(R.drawable.diamond13)

            "heart1" -> cardComImageView5.setImageResource(R.drawable.heart1)
            "heart2" -> cardComImageView5.setImageResource(R.drawable.heart2)
            "heart3" -> cardComImageView5.setImageResource(R.drawable.heart3)
            "heart4" -> cardComImageView5.setImageResource(R.drawable.heart4)
            "heart5" -> cardComImageView5.setImageResource(R.drawable.heart5)
            "heart6" -> cardComImageView5.setImageResource(R.drawable.heart6)
            "heart7" -> cardComImageView5.setImageResource(R.drawable.heart7)
            "heart8" -> cardComImageView5.setImageResource(R.drawable.heart8)
            "heart9" -> cardComImageView5.setImageResource(R.drawable.heart9)
            "heart10" -> cardComImageView5.setImageResource(R.drawable.heart10)
            "heart11" -> cardComImageView5.setImageResource(R.drawable.heart11)
            "heart12" -> cardComImageView5.setImageResource(R.drawable.heart12)
            "heart13" -> cardComImageView5.setImageResource(R.drawable.heart13)

            "spade1" -> cardComImageView5.setImageResource(R.drawable.spade1)
            "spade2" -> cardComImageView5.setImageResource(R.drawable.spade2)
            "spade3" -> cardComImageView5.setImageResource(R.drawable.spade3)
            "spade4" -> cardComImageView5.setImageResource(R.drawable.spade4)
            "spade5" -> cardComImageView5.setImageResource(R.drawable.spade5)
            "spade6" -> cardComImageView5.setImageResource(R.drawable.spade6)
            "spade7" -> cardComImageView5.setImageResource(R.drawable.spade7)
            "spade8" -> cardComImageView5.setImageResource(R.drawable.spade8)
            "spade9" -> cardComImageView5.setImageResource(R.drawable.spade9)
            "spade10" -> cardComImageView5.setImageResource(R.drawable.spade10)
            "spade11" -> cardComImageView5.setImageResource(R.drawable.spade11)
            "spade12" -> cardComImageView5.setImageResource(R.drawable.spade12)
            "spade13" -> cardComImageView5.setImageResource(R.drawable.spade13)
        }
        cardComSet5 = "set"
    }

    private fun intentSet() {
        Log.d("kotlintest", "通過5")
        if (myRound == preFlopNum) {
            Log.d("kotlintest", "通過6")
            roundPlayer = "you"
        } else {
            Log.d("kotlintest", "通過7")
            roundPlayer = "other"
        }

        Log.d("kotlintest", "通過8")
        when (roundPlayer) {
            "you" -> {
                Log.d("kotlintest", "通過9")
                Log.d("kotlintest", "自分・" + "memberNum：" + memberNum)
                Log.d("kotlintest", "自分・" + "count：" + count)
                Log.d("kotlintest", "自分・" + "round：" + roundCheck)
                Log.d("kotlintest", "自分・" + "round_count：" + round_count)
                Log.d("kotlintest", "自分・" + "roundNum：" + roundNum)
                Log.d("kotlintest", "自分・" + "myRound：" + myRound)
                Log.d("kotlintest", "自分・" + "tableChips：" + tableChips)
                Log.d("kotlintest", "自分・" + "tableTotalChips：" + tableTotalChips)
                Log.d("kotlintest", "自分・" + "playingNum：" + playingNum)
                Log.d("kotlintest", "自分・" + "foldPlayer：" + foldPlayer)
                Log.d("kotlintest", "自分・" + "sameChipsPlayer：" + sameChipsPlayer)

                // PlayingActivityへ移動
                val intent = Intent(this@CardActivity, PlayingActivity::class.java)
                intent.putExtra("memberNum", memberNum)
                intent.putExtra("game_id", game_id)
                intent.putExtra("count", count)
                intent.putExtra("round", roundCheck)
                intent.putExtra("round_count", round_count)
                intent.putExtra("roundNum", roundNum)
                intent.putExtra("myRound", myRound)
                intent.putExtra("cardHand1", cardHand1)
                intent.putExtra("cardHand2", cardHand2)
                intent.putExtra("cardCom1", cardCom1)
                intent.putExtra("cardCom2", cardCom2)
                intent.putExtra("cardCom3", cardCom3)
                intent.putExtra("cardCom4", cardCom4)
                intent.putExtra("cardCom5", cardCom5)
                intent.putExtra("bigBlind", bigBlind)
                intent.putExtra("tableChips", tableChips)
                intent.putExtra("tableTotalChips", tableTotalChips)
                intent.putExtra("flopNum", flopNum)
                intent.putExtra("preFlopNum", preFlopNum)
                intent.putExtra("playingNum", playingNum)
                intent.putExtra("foldPlayer", foldPlayer)
                intent.putExtra("sameChipsPlayer", sameChipsPlayer)
                startActivity(intent)
            }

            "other" -> {
                Log.d("kotlintest", "通過10")
                Log.d("kotlintest", "自分・" + "memberNum：" + memberNum)
                Log.d("kotlintest", "自分・" + "count：" + count)
                Log.d("kotlintest", "自分・" + "round：" + roundCheck)
                Log.d("kotlintest", "自分・" + "round_count：" + round_count)
                Log.d("kotlintest", "自分・" + "roundNum：" + roundNum)
                Log.d("kotlintest", "自分・" + "myRound：" + myRound)
                Log.d("kotlintest", "自分・" + "tableChips：" + tableChips)
                Log.d("kotlintest", "自分・" + "tableTotalChips：" + tableTotalChips)
                Log.d("kotlintest", "自分・" + "playingNum：" + playingNum)
                Log.d("kotlintest", "自分・" + "foldPlayer：" + foldPlayer)
                Log.d("kotlintest", "自分・" + "sameChipsPlayer：" + sameChipsPlayer)

                //MemberPlayingActivityへ移動
                val intent = Intent(this@CardActivity, MemberPlayingActivity::class.java)
                intent.putExtra("memberNum", memberNum)
                intent.putExtra("game_id", game_id)
                intent.putExtra("count", count)
                intent.putExtra("round", roundCheck)
                intent.putExtra("round_count", round_count)
                intent.putExtra("roundNum", roundNum)
                intent.putExtra("myRound", myRound)
                intent.putExtra("cardHand1", cardHand1)
                intent.putExtra("cardHand2", cardHand2)
                intent.putExtra("cardCom1", cardCom1)
                intent.putExtra("cardCom2", cardCom2)
                intent.putExtra("cardCom3", cardCom3)
                intent.putExtra("cardCom4", cardCom4)
                intent.putExtra("cardCom5", cardCom5)
                intent.putExtra("bigBlind", bigBlind)
                intent.putExtra("tableChips", tableChips)
                intent.putExtra("tableTotalChips", tableTotalChips)
                intent.putExtra("flopNum", flopNum)
                intent.putExtra("preFlopNum", preFlopNum)
                intent.putExtra("playingNum", playingNum)
                intent.putExtra("foldPlayer", foldPlayer)
                intent.putExtra("sameChipsPlayer", sameChipsPlayer)
                startActivity(intent)
            }
        }
    }

    private fun intentShowDown() {
        Log.d("kotlintest", "通過10")
        Log.d("kotlintest", "自分・" + "memberNum：" + memberNum)
        Log.d("kotlintest", "自分・" + "count：" + count)
        Log.d("kotlintest", "自分・" + "round：" + roundCheck)
        Log.d("kotlintest", "自分・" + "round_count：" + round_count)
        Log.d("kotlintest", "自分・" + "roundNum：" + roundNum)
        Log.d("kotlintest", "自分・" + "myRound：" + myRound)
        Log.d("kotlintest", "自分・" + "tableChips：" + tableChips)
        Log.d("kotlintest", "自分・" + "tableTotalChips：" + tableTotalChips)
        Log.d("kotlintest", "自分・" + "playingNum：" + playingNum)
        Log.d("kotlintest", "自分・" + "foldPlayer：" + foldPlayer)
        Log.d("kotlintest", "自分・" + "sameChipsPlayer：" + sameChipsPlayer)

        //ShowDownActivityへ移動
        val intent = Intent(this@CardActivity, ShowDownActivity::class.java)
        intent.putExtra("game_id", game_id)
        intent.putExtra("count", count)
        intent.putExtra("round", roundCheck)
        intent.putExtra("round_count", round_count)
        intent.putExtra("roundNum", roundNum)
        intent.putExtra("myRound", myRound)
        intent.putExtra("cardHand1", cardHand1)
        intent.putExtra("cardHand2", cardHand2)
        intent.putExtra("cardCom1", cardCom1)
        intent.putExtra("cardCom2", cardCom2)
        intent.putExtra("cardCom3", cardCom3)
        intent.putExtra("cardCom4", cardCom4)
        intent.putExtra("cardCom5", cardCom5)
        intent.putExtra("bigBlind", bigBlind)
        intent.putExtra("tableChips", tableChips)
        intent.putExtra("tableTotalChips", tableTotalChips)
        intent.putExtra("flopNum", flopNum)
        intent.putExtra("preFlopNum", preFlopNum)
        intent.putExtra("playingNum", playingNum)
        intent.putExtra("foldPlayer", foldPlayer)
        intent.putExtra("sameChipsPlayer", sameChipsPlayer)
        startActivity(intent)
    }
}
