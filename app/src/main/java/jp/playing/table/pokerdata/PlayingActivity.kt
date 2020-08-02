package jp.playing.table.pokerdata

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_hand.*
import kotlinx.android.synthetic.main.activity_member_playing.*
import kotlinx.android.synthetic.main.activity_playing.*

class PlayingActivity : AppCompatActivity() {

    private var mTurn: Turn? = null

    private var mMember: Member? = null

    private lateinit var mRealm: Realm

    //ハンド選択

    private var chipsNum1 = ""
    private var chipsNum2 = ""
    private var chipsNum3 = ""
    private var chipsNum4 = ""
    private var chipsNum5 = ""
    private var chipsNum6 = ""
    private var chipsNum7 = ""

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
    private var smallBlind = 0
    private var tableChips = 0
    private var tableTotalChips = 0
    private var startNum = 0
    private var flopNum = 0
    private var preFlopNum = 0
    private var playingNum = 0
    private var foldPlayer = 0
    private var sameChipsPlayer = 0

    private var memberChips = 0
    private var playChips = 0
    private var player_id = ""
    private var playingCheck = ""

    private var turnDataChips = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playing)

        supportActionBar?.title = "あなたの順番です"

        //Realmの設定
        mRealm = Realm.getDefaultInstance()

        val memberChipsRealm = mRealm.where(Member::class.java).findAll()
        val memberChipsRealmId = memberChipsRealm.max("id")!!.toInt()

        memberChips = memberChipsRealm[memberChipsRealmId]!!.memberChips


        val intent = intent
        memberNum = intent.getIntExtra("memberNum", 0)
        game_id = intent.getIntExtra("game_id", 0)
        count = intent.getIntExtra("count", 0)
        round = intent.getStringExtra("round")
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
        smallBlind = intent.getIntExtra("smallBlind", 0)
        tableChips = intent.getIntExtra("tableChips", 0)
        tableTotalChips = intent.getIntExtra("tableTotalChips", 0)
        flopNum = intent.getIntExtra("flopNum", 0)
        preFlopNum = intent.getIntExtra("preFlopNum", 0)
        playingNum = intent.getIntExtra("playingNum", 0)
        foldPlayer = intent.getIntExtra("foldPlayer", 0)
        sameChipsPlayer = intent.getIntExtra("sameChipsPlayer", 0)

        if (round == "flop") {
            startNum = flopNum
        } else {
            startNum = preFlopNum
        }

        val memberRealmResults = mRealm.where(Member::class.java).findAll()
        val memberPlayerNumRealmResults = mRealm.where(Member::class.java).equalTo("memberRound", playingNum).findAll()
        val playerNumId = memberPlayerNumRealmResults.max("id")!!.toInt()
        val memberPlayingCheck = memberRealmResults[playerNumId]!!.playingCheck
        val dataPlayerId = memberRealmResults[playerNumId]!!.member_id

        if (memberPlayingCheck == "fold") {
            if (sameChipsPlayer == memberNum - foldPlayer) {
                when (round) {
                    "preflop" -> round = "flop"
                    "flop" -> round = "turn"
                    "turn" -> round = "river"
                    "turn" -> round = "showdown"
                }

                // CardActivityへ移動
                val intent = Intent(this@PlayingActivity, CardActivity::class.java)
                intent.putExtra("memberNum", memberNum)
                intent.putExtra("game_id", game_id)
                intent.putExtra("count", count)
                intent.putExtra("round", round)
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
                intent.putExtra("smallBlind", smallBlind)
                intent.putExtra("tableChips", tableChips)
                intent.putExtra("tableTotalChips", tableTotalChips)
                intent.putExtra("flopNum", flopNum)
                intent.putExtra("preFlopNum", preFlopNum)
                intent.putExtra("playingNum", playingNum)
                intent.putExtra("foldPlayer", foldPlayer)
                intent.putExtra("sameChipsPlayer", sameChipsPlayer)
                startActivity(intent)

            }

            if (roundNum == memberNum) {
                round_count++
                roundNum = 1
            } else {
                roundNum++
            }

            if (playingNum == memberNum) {
                playingNum = 1
            } else {
                playingNum++
            }

            // MemberPlayingActivityに移動
            val intent = Intent(this@PlayingActivity, MemberPlayingActivity::class.java)
            intent.putExtra("memberNum", memberNum)
            intent.putExtra("game_id", game_id)
            intent.putExtra("count", count)
            intent.putExtra("round", round)
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
            intent.putExtra("smallBlind", smallBlind)
            intent.putExtra("tableChips", tableChips)
            intent.putExtra("tableTotalChips", tableTotalChips)
            intent.putExtra("flopNum", flopNum)
            intent.putExtra("preFlopNum", preFlopNum)
            intent.putExtra("playingNum", playingNum)
            intent.putExtra("foldPlayer", foldPlayer)
            intent.putExtra("sameChipsPlayer", sameChipsPlayer)
            startActivity(intent)
        }

        val turnRealmResults = mRealm.where(Turn::class.java).findAll()
        val turnDataRealmResults = mRealm.where(Turn::class.java).equalTo("player_id", dataPlayerId).findAll()
        if (turnDataRealmResults.toString() != "[]") {
            val turnDataRealmResultsId = turnDataRealmResults.max("id")!!.toInt()
            turnDataChips = turnRealmResults[turnDataRealmResultsId]!!.playChips
            var turnDataRound = turnRealmResults[turnDataRealmResultsId]!!.round

            if (turnDataChips == null || turnDataRound != round) {
                turnDataChips = 0
            }
        }

        playingHandText.text = "ミニマムベット：" + tableChips + "  " + "ミニマムレイズ：" + tableChips * 2

        if (round == "preflop" && round_count == 1) {
            playingChipsText.text = "0"
        } else {
            playingChipsText.text = turnDataChips.toString()
        }

        cardSetting1()
        cardSetting2()

        //アクションボタンのUI表示設定
        if (tableChips != playingChipsText.text.toString().toInt()) {
            playingCheckButton.isEnabled = false
        } else {
            playingCheckButton.isEnabled = true
        }

        if (playingChipsText.text.toString().toInt() < tableChips) {
            playingCallButton.isEnabled = true
        } else {
            playingCallButton.isEnabled = false
        }

        //UI部品の設定
        playingCheckButton.setOnClickListener {
            playingChipsText.text = playChips.toString()
            playingCheck = "play"
        }

        playingCallButton.setOnClickListener {
            playChips = tableChips
            playingChipsText.text = playChips.toString()
            playingCheck = "play"
        }

        playingFoldButton.setOnClickListener {
            playingChipsText.text = "0"
            playingCheck = "fold"
        }

        playingAllInButton.setOnClickListener {
            playChips = memberChips
            playingChipsText.text = playChips.toString()
            playingCheck = "play"
        }

        playingNum1.setOnClickListener {
            when {
                chipsNum1 == "" -> chipsNum1 = "1"
                chipsNum2 == "" -> chipsNum2 = "1"
                chipsNum3 == "" -> chipsNum3 = "1"
                chipsNum4 == "" -> chipsNum4 = "1"
                chipsNum5 == "" -> chipsNum5 = "1"
                chipsNum6 == "" -> chipsNum6 = "1"
                chipsNum7 == "" -> chipsNum7 = "1"


            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
        }

        playingNum2.setOnClickListener {
            when {
                chipsNum1 == "" -> chipsNum1 = "2"
                chipsNum2 == "" -> chipsNum2 = "2"
                chipsNum3 == "" -> chipsNum3 = "2"
                chipsNum4 == "" -> chipsNum4 = "2"
                chipsNum5 == "" -> chipsNum5 = "2"
                chipsNum6 == "" -> chipsNum6 = "2"
                chipsNum7 == "" -> chipsNum7 = "2"


            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
        }

        playingNum3.setOnClickListener {
            when {
                chipsNum1 == "" -> chipsNum1 = "3"
                chipsNum2 == "" -> chipsNum2 = "3"
                chipsNum3 == "" -> chipsNum3 = "3"
                chipsNum4 == "" -> chipsNum4 = "3"
                chipsNum5 == "" -> chipsNum5 = "3"
                chipsNum6 == "" -> chipsNum6 = "3"
                chipsNum7 == "" -> chipsNum7 = "3"


            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
        }

        playingNum4.setOnClickListener {
            when {
                chipsNum1 == "" -> chipsNum1 = "4"
                chipsNum2 == "" -> chipsNum2 = "4"
                chipsNum3 == "" -> chipsNum3 = "4"
                chipsNum4 == "" -> chipsNum4 = "4"
                chipsNum5 == "" -> chipsNum5 = "4"
                chipsNum6 == "" -> chipsNum6 = "4"
                chipsNum7 == "" -> chipsNum7 = "4"


            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
        }

        playingNum5.setOnClickListener {
            when {
                chipsNum1 == "" -> chipsNum1 = "5"
                chipsNum2 == "" -> chipsNum2 = "5"
                chipsNum3 == "" -> chipsNum3 = "5"
                chipsNum4 == "" -> chipsNum4 = "5"
                chipsNum5 == "" -> chipsNum5 = "5"
                chipsNum6 == "" -> chipsNum6 = "5"
                chipsNum7 == "" -> chipsNum7 = "5"


            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
        }

        playingNum6.setOnClickListener {
            when {
                chipsNum1 == "" -> chipsNum1 = "6"
                chipsNum2 == "" -> chipsNum2 = "6"
                chipsNum3 == "" -> chipsNum3 = "6"
                chipsNum4 == "" -> chipsNum4 = "6"
                chipsNum5 == "" -> chipsNum5 = "6"
                chipsNum6 == "" -> chipsNum6 = "6"
                chipsNum7 == "" -> chipsNum7 = "6"


            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
        }

        playingNum7.setOnClickListener {
            when {
                chipsNum1 == "" -> chipsNum1 = "7"
                chipsNum2 == "" -> chipsNum2 = "7"
                chipsNum3 == "" -> chipsNum3 = "7"
                chipsNum4 == "" -> chipsNum4 = "7"
                chipsNum5 == "" -> chipsNum5 = "7"
                chipsNum6 == "" -> chipsNum6 = "7"
                chipsNum7 == "" -> chipsNum7 = "7"

            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
        }

        playingNum8.setOnClickListener {
            when {
                chipsNum1 == "" -> chipsNum1 = "8"
                chipsNum2 == "" -> chipsNum2 = "8"
                chipsNum3 == "" -> chipsNum3 = "8"
                chipsNum4 == "" -> chipsNum4 = "8"
                chipsNum5 == "" -> chipsNum5 = "8"
                chipsNum6 == "" -> chipsNum6 = "8"
                chipsNum7 == "" -> chipsNum7 = "8"


            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
        }

        playingNum9.setOnClickListener {
            when {
                chipsNum1 == "" -> chipsNum1 = "9"
                chipsNum2 == "" -> chipsNum2 = "9"
                chipsNum3 == "" -> chipsNum3 = "9"
                chipsNum4 == "" -> chipsNum4 = "9"
                chipsNum5 == "" -> chipsNum5 = "9"
                chipsNum6 == "" -> chipsNum6 = "9"
                chipsNum7 == "" -> chipsNum7 = "9"


            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
        }

        playingNum0.setOnClickListener {
            when {
                chipsNum2 == "" -> chipsNum2 = "0"
                chipsNum3 == "" -> chipsNum3 = "0"
                chipsNum4 == "" -> chipsNum4 = "0"
                chipsNum5 == "" -> chipsNum5 = "0"
                chipsNum6 == "" -> chipsNum6 = "0"
                chipsNum7 == "" -> chipsNum7 = "0"
            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
        }

        playingNum00.setOnClickListener {
            when {
                chipsNum2 == "" -> {
                    chipsNum2 = "0"
                    chipsNum3 = "0"
                }
                chipsNum3 == "" -> {
                    chipsNum3 = "0"
                    chipsNum4 = "0"
                }
                chipsNum4 == "" -> {
                    chipsNum4 = "0"
                    chipsNum5 = "0"
                }
                chipsNum5 == "" -> {
                    chipsNum5 = "0"
                    chipsNum6 = "0"
                }
                chipsNum6 == "" -> {
                    chipsNum6 = "0"
                    chipsNum7 = "0"
                }
                chipsNum7 == "" -> {
                    chipsNum7 = "0"
                }
            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
        }

        playingNum000.setOnClickListener {
            when {
                chipsNum2 == "" -> {
                    chipsNum2 = "0"
                    chipsNum3 = "0"
                    chipsNum4 = "0"
                }
                chipsNum3 == "" -> {
                    chipsNum3 = "0"
                    chipsNum4 = "0"
                    chipsNum5 = "0"
                }
                chipsNum4 == "" -> {
                    chipsNum4 = "0"
                    chipsNum5 = "0"
                    chipsNum6 = "0"
                }
                chipsNum5 == "" -> {
                    chipsNum5 = "0"
                    chipsNum6 = "0"
                    chipsNum7 = "0"
                }
                chipsNum6 == "" -> {
                    chipsNum6 = "0"
                    chipsNum7 = "0"
                }
                chipsNum7 == "" -> {
                    chipsNum7 = "0"
                }
            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
        }

        playingNum0000.setOnClickListener {
            when {
                chipsNum2 == "" -> {
                    chipsNum2 = "0"
                    chipsNum3 = "0"
                    chipsNum4 = "0"
                    chipsNum5 = "0"
                }
                chipsNum3 == "" -> {
                    chipsNum3 = "0"
                    chipsNum4 = "0"
                    chipsNum5 = "0"
                    chipsNum6 = "0"
                }
                chipsNum4 == "" -> {
                    chipsNum4 = "0"
                    chipsNum5 = "0"
                    chipsNum6 = "0"
                    chipsNum7 = "0"
                }
                chipsNum5 == "" -> {
                    chipsNum5 = "0"
                    chipsNum6 = "0"
                    chipsNum7 = "0"
                }
                chipsNum6 == "" -> {
                    chipsNum6 = "0"
                    chipsNum7 = "0"
                }
                chipsNum7 == "" -> {
                    chipsNum7 = "0"
                }
            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
        }

        playingDaleteButton.setOnClickListener {
            chipsNum1 = ""
            chipsNum2 = ""
            chipsNum3 = ""
            chipsNum4 = ""
            chipsNum5 = ""
            chipsNum6 = ""
            chipsNum7 = ""
            playingCheck = "play"

            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
        }

        playingDoneButton.setOnClickListener {
            //チップ値が入力されているか確認
            if (playingChipsText.text != "") {
                tableTotalChips = tableTotalChips + playingChipsText.text.toString().toInt()
                //テーブルチップとの比較
                when {
                    playingChipsText.text.toString().toInt() == tableChips -> {
                        sameChipsPlayer++
                    }
                    playingChipsText.text.toString().toInt() > tableChips -> {
                        sameChipsPlayer = 1
                        tableChips = playingChipsText.text.toString().toInt()
                    }
                 }
                mRealm.beginTransaction()
                val turnRealmResults = mRealm.where(Turn::class.java).findAll()

                //Turnへデータ保存
                if (mTurn == null) {
                    mTurn = Turn()

                    val identifier: Int =
                        if (turnRealmResults.max("id") != null) {
                            turnRealmResults.max("id")!!.toInt() + 1
                        } else {
                            0
                        }
                    mTurn!!.id = identifier
                }

                mMember = Member()

//                val memberRealmResults = mRealm.where(Member::class.java).findAll()
                //player_idを取得
//                val memberPlayerNumRealmResults = mRealm.where(Member::class.java).equalTo("memberRound", playingNum).findAll()
//                val playerNumId = memberPlayerNumRealmResults.max("id")!!.toInt()
                player_id = memberRealmResults[playerNumId]!!.member_id
                val playerTotalChips = memberRealmResults[playerNumId]!!.memberChips

                //Turnを新規登録
                mTurn!!.game_id = game_id
                mTurn!!.count = count
                mTurn!!.round = round
                mTurn!!.round_count = round_count
                mTurn!!.player = "自分"
                mTurn!!.player_id = player_id
                mTurn!!.playChips = playingChipsText.text.toString().toInt() - turnDataChips.toString().toInt()
                mTurn!!.memberChips = playerTotalChips - playingChipsText.text.toString().toInt()
                mTurn!!.tableChips = tableChips
                mTurn!!.tableTotalChips = tableTotalChips


                //Memberを更新
                var menber = mRealm.where(Member::class.java).equalTo("id", playerNumId ).findFirst()


                if (playingCheck == "fold"){
                    menber!!.playingCheck = "fold"
                    foldPlayer++

                }

                menber!!.playChips = playingChipsText.text.toString().toInt() - turnDataChips.toString().toInt()
                menber!!.tableChips = tableChips
                menber!!.tableTotalChips = tableTotalChips
                menber!!.memberChips = playerTotalChips - playingChipsText.text.toString().toInt()

                mRealm.copyToRealmOrUpdate(mTurn!!)
                mRealm.copyToRealmOrUpdate(mMember!!)
                mRealm.commitTransaction()

                if (foldPlayer == memberNum - 1) {
                    count++
                    if (flopNum == memberNum) {
                        flopNum = 1
                        playingNum = 1
                    } else {
                        flopNum++
                        playingNum = flopNum
                    }
                    // HandActivityへ移動
                    val intent = Intent(this@PlayingActivity, HandActivity::class.java)
                    intent.putExtra("memberNum", memberNum)
                    intent.putExtra("count", count)
                    intent.putExtra("myRound", myRound)
                    intent.putExtra("bigBlind", bigBlind)
                    intent.putExtra("smallBlind", smallBlind)
                    intent.putExtra("flopNum", flopNum)
                    intent.putExtra("playingNum", playingNum)
                    startActivity(intent)

                } else {
                    if (sameChipsPlayer == memberNum - foldPlayer) {
                        when (round) {
                            "preflop" -> round = "flop"
                            "flop" -> round = "turn"
                            "turn" -> round = "river"
                            "river" -> round = "showdown"
                        }

                        playingNum = preFlopNum
                        roundNum = 1
                        round_count = 1
                        tableChips = 0
                        sameChipsPlayer = 0

                        // CardActivityへ移動

                        val intent = Intent(this@PlayingActivity, CardActivity::class.java)
                        intent.putExtra("memberNum", memberNum)
                        intent.putExtra("game_id", game_id)
                        intent.putExtra("count", count)
                        intent.putExtra("round", round)
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
                        intent.putExtra("smallBlind", smallBlind)
                        intent.putExtra("tableChips", tableChips)
                        intent.putExtra("tableTotalChips", tableTotalChips)
                        intent.putExtra("flopNum", flopNum)
                        intent.putExtra("preFlopNum", preFlopNum)
                        intent.putExtra("playingNum", playingNum)
                        intent.putExtra("foldPlayer", foldPlayer)
                        intent.putExtra("sameChipsPlayer", sameChipsPlayer)
                        startActivity(intent)


                    } else {

                        if (roundNum == memberNum) {
                            round_count++
                            roundNum = 1
                        } else {
                            roundNum++
                        }

                        if (playingNum == memberNum) {
                            playingNum = 1
                        } else {
                            playingNum++
                        }

                        // MemberPlayingActivityに移動

                        val intent = Intent(this@PlayingActivity, MemberPlayingActivity::class.java)
                        intent.putExtra("memberNum", memberNum)
                        intent.putExtra("game_id", game_id)
                        intent.putExtra("count", count)
                        intent.putExtra("round", round)
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
                        intent.putExtra("smallBlind", smallBlind)
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
        }

    }

    private fun cardSetting1() {
        when (cardHand1){
            "club1" -> playingHand1.setImageResource(R.drawable.club1)
            "club2" -> playingHand1.setImageResource(R.drawable.club2)
            "club3" -> playingHand1.setImageResource(R.drawable.club3)
            "club4" -> playingHand1.setImageResource(R.drawable.club4)
            "club5" -> playingHand1.setImageResource(R.drawable.club5)
            "club6" -> playingHand1.setImageResource(R.drawable.club6)
            "club7" -> playingHand1.setImageResource(R.drawable.club7)
            "club8" -> playingHand1.setImageResource(R.drawable.club8)
            "club9" -> playingHand1.setImageResource(R.drawable.club9)
            "club10" -> playingHand1.setImageResource(R.drawable.club10)
            "club11" -> playingHand1.setImageResource(R.drawable.club11)
            "club12" -> playingHand1.setImageResource(R.drawable.club12)
            "club13" -> playingHand1.setImageResource(R.drawable.club13)

            "diamond1" -> playingHand1.setImageResource(R.drawable.diamond1)
            "diamond2" -> playingHand1.setImageResource(R.drawable.diamond2)
            "diamond3" -> playingHand1.setImageResource(R.drawable.diamond3)
            "diamond4" -> playingHand1.setImageResource(R.drawable.diamond4)
            "diamond5" -> playingHand1.setImageResource(R.drawable.diamond5)
            "diamond6" -> playingHand1.setImageResource(R.drawable.diamond6)
            "diamond7" -> playingHand1.setImageResource(R.drawable.diamond7)
            "diamond8" -> playingHand1.setImageResource(R.drawable.diamond8)
            "diamond9" -> playingHand1.setImageResource(R.drawable.diamond9)
            "diamond10" -> playingHand1.setImageResource(R.drawable.diamond10)
            "diamond11" -> playingHand1.setImageResource(R.drawable.diamond11)
            "diamond12" -> playingHand1.setImageResource(R.drawable.diamond12)
            "diamond13" -> playingHand1.setImageResource(R.drawable.diamond13)

            "heart1" -> playingHand1.setImageResource(R.drawable.heart1)
            "heart2" -> playingHand1.setImageResource(R.drawable.heart2)
            "heart3" -> playingHand1.setImageResource(R.drawable.heart3)
            "heart4" -> playingHand1.setImageResource(R.drawable.heart4)
            "heart5" -> playingHand1.setImageResource(R.drawable.heart5)
            "heart6" -> playingHand1.setImageResource(R.drawable.heart6)
            "heart7" -> playingHand1.setImageResource(R.drawable.heart7)
            "heart8" -> playingHand1.setImageResource(R.drawable.heart8)
            "heart9" -> playingHand1.setImageResource(R.drawable.heart9)
            "heart10" -> playingHand1.setImageResource(R.drawable.heart10)
            "heart11" -> playingHand1.setImageResource(R.drawable.heart11)
            "heart12" -> playingHand1.setImageResource(R.drawable.heart12)
            "heart13" -> playingHand1.setImageResource(R.drawable.heart13)

            "spade1" -> playingHand1.setImageResource(R.drawable.spade1)
            "spade2" -> playingHand1.setImageResource(R.drawable.spade2)
            "spade3" -> playingHand1.setImageResource(R.drawable.spade3)
            "spade4" -> playingHand1.setImageResource(R.drawable.spade4)
            "spade5" -> playingHand1.setImageResource(R.drawable.spade5)
            "spade6" -> playingHand1.setImageResource(R.drawable.spade6)
            "spade7" -> playingHand1.setImageResource(R.drawable.spade7)
            "spade8" -> playingHand1.setImageResource(R.drawable.spade8)
            "spade9" -> playingHand1.setImageResource(R.drawable.spade9)
            "spade10" -> playingHand1.setImageResource(R.drawable.spade10)
            "spade11" -> playingHand1.setImageResource(R.drawable.spade11)
            "spade12" -> playingHand1.setImageResource(R.drawable.spade12)
            "spade13" -> playingHand1.setImageResource(R.drawable.spade13)
        }
    }

    private fun cardSetting2() {
        when(cardHand2) {
            "club1" -> playingHand2.setImageResource(R.drawable.club1)
            "club2" -> playingHand2.setImageResource(R.drawable.club2)
            "club3" -> playingHand2.setImageResource(R.drawable.club3)
            "club4" -> playingHand2.setImageResource(R.drawable.club4)
            "club5" -> playingHand2.setImageResource(R.drawable.club5)
            "club6" -> playingHand2.setImageResource(R.drawable.club6)
            "club7" -> playingHand2.setImageResource(R.drawable.club7)
            "club8" -> playingHand2.setImageResource(R.drawable.club8)
            "club9" -> playingHand2.setImageResource(R.drawable.club9)
            "club10" -> playingHand2.setImageResource(R.drawable.club10)
            "club11" -> playingHand2.setImageResource(R.drawable.club11)
            "club12" -> playingHand2.setImageResource(R.drawable.club12)
            "club13" -> playingHand2.setImageResource(R.drawable.club13)

            "diamond1" -> playingHand2.setImageResource(R.drawable.diamond1)
            "diamond2" -> playingHand2.setImageResource(R.drawable.diamond2)
            "diamond3" -> playingHand2.setImageResource(R.drawable.diamond3)
            "diamond4" -> playingHand2.setImageResource(R.drawable.diamond4)
            "diamond5" -> playingHand2.setImageResource(R.drawable.diamond5)
            "diamond6" -> playingHand2.setImageResource(R.drawable.diamond6)
            "diamond7" -> playingHand2.setImageResource(R.drawable.diamond7)
            "diamond8" -> playingHand2.setImageResource(R.drawable.diamond8)
            "diamond9" -> playingHand2.setImageResource(R.drawable.diamond9)
            "diamond10" -> playingHand2.setImageResource(R.drawable.diamond10)
            "diamond11" -> playingHand2.setImageResource(R.drawable.diamond11)
            "diamond12" -> playingHand2.setImageResource(R.drawable.diamond12)
            "diamond13" -> playingHand2.setImageResource(R.drawable.diamond13)

            "heart1" -> playingHand2.setImageResource(R.drawable.heart1)
            "heart2" -> playingHand2.setImageResource(R.drawable.heart2)
            "heart3" -> playingHand2.setImageResource(R.drawable.heart3)
            "heart4" -> playingHand2.setImageResource(R.drawable.heart4)
            "heart5" -> playingHand2.setImageResource(R.drawable.heart5)
            "heart6" -> playingHand2.setImageResource(R.drawable.heart6)
            "heart7" -> playingHand2.setImageResource(R.drawable.heart7)
            "heart8" -> playingHand2.setImageResource(R.drawable.heart8)
            "heart9" -> playingHand2.setImageResource(R.drawable.heart9)
            "heart10" -> playingHand2.setImageResource(R.drawable.heart10)
            "heart11" -> playingHand2.setImageResource(R.drawable.heart11)
            "heart12" -> playingHand2.setImageResource(R.drawable.heart12)
            "heart13" -> playingHand2.setImageResource(R.drawable.heart13)

            "spade1" -> playingHand2.setImageResource(R.drawable.spade1)
            "spade2" -> playingHand2.setImageResource(R.drawable.spade2)
            "spade3" -> playingHand2.setImageResource(R.drawable.spade3)
            "spade4" -> playingHand2.setImageResource(R.drawable.spade4)
            "spade5" -> playingHand2.setImageResource(R.drawable.spade5)
            "spade6" -> playingHand2.setImageResource(R.drawable.spade6)
            "spade7" -> playingHand2.setImageResource(R.drawable.spade7)
            "spade8" -> playingHand2.setImageResource(R.drawable.spade8)
            "spade9" -> playingHand2.setImageResource(R.drawable.spade9)
            "spade10" -> playingHand2.setImageResource(R.drawable.spade10)
            "spade11" -> playingHand2.setImageResource(R.drawable.spade11)
            "spade12" -> playingHand2.setImageResource(R.drawable.spade12)
            "spade13" -> playingHand2.setImageResource(R.drawable.spade13)
        }
    }
}
