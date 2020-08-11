package jp.playing.table.pokerdata

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_member_playing.*
import kotlinx.android.synthetic.main.activity_playing.*

class MemberPlayingActivity : AppCompatActivity() {

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
    private var bigBlindNum = 0
    private var playingNum = 0
    private var foldPlayer = 0
    private var sameChipsPlayer = 0

    private var memberChips = 0
    private var turnMemberChips = 0
    private var playChips = 0
    private var playingCheck = ""

    private var playerName = ""
    private var player_id = ""

    private var turnDataChips = 0
    private var turnDataCount = 0
    private var turnDataRound = ""
    private var turnDataRoundCount = 0
    private var turnDataPlayer = ""

    private var firstRealm = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_playing)

        //Realmの設定
        mRealm = Realm.getDefaultInstance()

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
        bigBlindNum = intent.getIntExtra("bigBlindNum", 0)
        playingNum = intent.getIntExtra("playingNum", 0)
        foldPlayer = intent.getIntExtra("foldPlayer", 0)
        sameChipsPlayer = intent.getIntExtra("sameChipsPlayer", 0)
        firstRealm = intent.getStringExtra("firstRealm")

        Log.d("kotlintest", game_id.toString())


        if (round == "flop") {
            startNum = flopNum
        } else {
            startNum = preFlopNum
        }

        val memberPlayerRealmResults = mRealm.where(Member::class.java).findAll()
        val memberPlayerRoundRealmCount = mRealm.where(Member::class.java).count()
        Log.d("kotlintest", "カウント：" + memberPlayerRoundRealmCount.toString())
        val memberPlayerRoundRealmResults = mRealm.where(Member::class.java).equalTo("memberRound", playingNum).findAll()
        Log.d("kotlintest", memberPlayerRoundRealmResults.toString())
        val memberPlayerRealmResultsId = memberPlayerRoundRealmResults.max("id")!!.toInt()
        val memberRealmData = mRealm.where(Member::class.java).equalTo("id", memberPlayerRealmResultsId).findFirst()
        playerName = memberRealmData!!.memberName
        val memberPlayingCheck = memberRealmData!!.playingCheck
        val turnChips = memberRealmData!!.memberChips
//        Log.d("kotlintest", "受け取ったID：" + memberPlayerRealmResultsId)
//        playerName = memberPlayerRealmResults[memberPlayerRealmResultsId]!!.memberName
//        Log.d("kotlintest", playerName)
//        val memberPlayingCheck = memberPlayerRealmResults[memberPlayerRealmResultsId]!!.playingCheck
//        val turnChips = memberPlayerRealmResults[memberPlayerRealmResultsId]!!.memberChips

        if (memberPlayingCheck == "fold") {
            if (sameChipsPlayer == memberNum - foldPlayer) {
                when (round) {
                    "preflop" -> round = "flop"
                    "flop" -> round = "turn"
                    "turn" -> round = "river"
                    "turn" -> round = "showdown"
                }

                //CardActivityへ移動
                val intent = Intent(this@MemberPlayingActivity, CardActivity::class.java)
                Log.d("kotlintest", "通過MPA1")
                Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[count]:" + count.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[round]:" + round)
                Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[round_count]:" + round_count.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[roundNum]:" + roundNum.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[myRound]:" + myRound.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[flopNum]:" + flopNum.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[preFlopNum]:" + preFlopNum.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[bigBlindNum]" + bigBlindNum.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[playingNum]:" + playingNum.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[foldPlayer]:" + foldPlayer.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[sameChipsPlayer]:" + sameChipsPlayer.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[smallBlind]:" + smallBlind.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[bigBlind]:" + bigBlind.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[tableChips]:" + tableChips.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[tableTotalChips]:" + tableTotalChips.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[bigBlind]:" + bigBlind)
                Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[smallBlind]:" + smallBlind)
                Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[tableChips]:" + tableChips)
                Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[tableTotalChips]:" + tableTotalChips)

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
                intent.putExtra("bigBlindNum", bigBlindNum)
                intent.putExtra("playingNum", playingNum)
                intent.putExtra("foldPlayer", foldPlayer)
                intent.putExtra("sameChipsPlayer", sameChipsPlayer)
                intent.putExtra("firstRealm", firstRealm)
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

            val memberPlayerNumRealm = mRealm.where(Member::class.java).equalTo("memberRound", playingNum).findAll()
            val memberPlayerNumRealmId = memberPlayerNumRealm.max("id")!!.toInt()
            val nextPlayerName = memberPlayerRealmResults[memberPlayerNumRealmId]!!.memberName

            if (nextPlayerName == "自分") {
                //PlayingActivityに移動
                val intent = Intent(this@MemberPlayingActivity, PlayingActivity::class.java)
                Log.d("kotlintest", "通過MPA2")
                Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[count]:" + count.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[round]:" + round)
                Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[round_count]:" + round_count.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[roundNum]:" + roundNum.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[myRound]:" + myRound.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[flopNum]:" + flopNum.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[preFlopNum]:" + preFlopNum.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[bigBlindNum]" + bigBlindNum.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[playingNum]:" + playingNum.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[foldPlayer]:" + foldPlayer.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[sameChipsPlayer]:" + sameChipsPlayer.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[smallBlind]:" + smallBlind.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[bigBlind]:" + bigBlind.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[tableChips]:" + tableChips.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[tableTotalChips]:" + tableTotalChips.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[bigBlind]:" + bigBlind)
                Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[smallBlind]:" + smallBlind)
                Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[tableChips]:" + tableChips)
                Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[tableTotalChips]:" + tableTotalChips)


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
                intent.putExtra("bigBlindNum", bigBlindNum)
                intent.putExtra("playingNum", playingNum)
                intent.putExtra("foldPlayer", foldPlayer)
                intent.putExtra("sameChipsPlayer", sameChipsPlayer)
                intent.putExtra("firstRealm", firstRealm)
                startActivity(intent)
            } else {
                //MemberPlayingActivityに移動
                val intent = Intent(this@MemberPlayingActivity, MemberPlayingActivity::class.java)
                Log.d("kotlintest", "通過MPA3")
                Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[count]:" + count.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[round]:" + round)
                Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[round_count]:" + round_count.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[roundNum]:" + roundNum.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[myRound]:" + myRound.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[flopNum]:" + flopNum.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[preFlopNum]:" + preFlopNum.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[bigBlindNum]" + bigBlindNum.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[playingNum]:" + playingNum.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[foldPlayer]:" + foldPlayer.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[sameChipsPlayer]:" + sameChipsPlayer.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[smallBlind]:" + smallBlind.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[bigBlind]:" + bigBlind.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[tableChips]:" + tableChips.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[tableTotalChips]:" + tableTotalChips.toString())
                Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[bigBlind]:" + bigBlind)
                Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[smallBlind]:" + smallBlind)
                Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[tableChips]:" + tableChips)
                Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[tableTotalChips]:" + tableTotalChips)

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
                intent.putExtra("bigBlindNum", bigBlindNum)
                intent.putExtra("playingNum", playingNum)
                intent.putExtra("foldPlayer", foldPlayer)
                intent.putExtra("sameChipsPlayer", sameChipsPlayer)
                intent.putExtra("firstRealm", firstRealm)
                startActivity(intent)
            }
        }


        val turnRealmResults = mRealm.where(Turn::class.java).findAll()
        val turnDataRealmResults = mRealm.where(Turn::class.java).equalTo("player", playerName).findAll()
        if (turnDataRealmResults.toString() != "[]") {
            val turnDataRealmResultsId = turnDataRealmResults.max("id")!!.toInt()
            turnDataChips = turnRealmResults[turnDataRealmResultsId]!!.playChips
            turnDataRound = turnRealmResults[turnDataRealmResultsId]!!.round //前回の自分のラウンド
            turnDataCount = turnRealmResults[turnDataRealmResultsId]!!.count //前回のゲーム数
            turnDataRoundCount = turnRealmResults[turnDataRealmResultsId]!!.round_count //ラウンド内の周数
            turnDataPlayer = turnRealmResults[turnDataRealmResultsId]!!.player //プレイヤー名

            if (turnDataChips == null || turnDataRound != round) {
                turnDataChips = 0
            }
        }

        supportActionBar?.title = playerName

        memberPlayingNameText.text = "ミニマムベット：" + tableChips + " " + "ポット：" + tableTotalChips

        Log.d("kotlintest", turnDataChips.toString())

        memberChangeChipsText.text = "0"

//        if (round == "preflop" && round_count == 1) {
//            memberChangeChipsText.text = "0"
//        } else {
//            memberChangeChipsText.text = turnDataChips.toString()
//        }


        //アクションボタンのUI表示設定
        if (tableChips != memberChangeChipsText.text.toString().toInt()) {
            memberPlayingCheckButton.isEnabled = false
        } else {
            memberPlayingCheckButton.isEnabled = true
        }

        if (memberChangeChipsText.text.toString().toInt() < tableChips) {
            memberPlayingCallButton.isEnabled = true
        } else {
            memberPlayingCallButton.isEnabled = false
        }



        //UI部品の設定
        memberPlayingCheckButton.setOnClickListener {
            chipsNum1 = ""
            chipsNum2 = ""
            chipsNum3 = ""
            chipsNum4 = ""
            chipsNum5 = ""
            chipsNum6 = ""
            chipsNum7 = ""
            memberChangeChipsText.text = playChips.toString()
            playingCheck = "play"
            memberPlayingFoldText.text = ""
        }

        memberPlayingCallButton.setOnClickListener {
            chipsNum1 = ""
            chipsNum2 = ""
            chipsNum3 = ""
            chipsNum4 = ""
            chipsNum5 = ""
            chipsNum6 = ""
            chipsNum7 = ""
            Log.d("kotlintest", "turnDataCount:" + turnDataCount.toString())
            Log.d("kotlintest", "count:" + count.toString())
            Log.d("kotlintest", "turnDataRound:" + turnDataRound)
            Log.d("kotlintest", "round:" + round)
            Log.d("kotlintest", "turnDataRoundCount:" + turnDataRoundCount.toString())
            Log.d("kotlintest", "round_count:" + round_count.toString())
            Log.d("kotlintest", "turnDataPlayer:" + turnDataPlayer)
            Log.d("kotlintest", "playerName:" + playerName)
            Log.d("kotlintest", "tableChips:" + tableChips.toString())
            Log.d("kotlintest", "turnDataChips:" + turnDataChips.toString() )
            Log.d("kotlintest", "playChips:" + playChips.toString())

            if (turnDataCount == count && turnDataRound == round && turnDataRoundCount + 1 == round_count && turnDataPlayer == playerName) {
                Log.d("kotlintest", "playChips = tableChips - turnDataChips")
                playChips = tableChips - turnDataChips
            } else {
                Log.d("kotlintest", "playChips = tableChips")
                playChips = tableChips
            }

            Log.d("kotlintest", "turnDataCount:" + turnDataCount.toString())
            Log.d("kotlintest", "count:" + count.toString())
            Log.d("kotlintest", "turnDataRound:" + turnDataRound)
            Log.d("kotlintest", "round:" + round)
            Log.d("kotlintest", "turnDataRoundCount:" + turnDataRoundCount.toString())
            Log.d("kotlintest", "round_count:" + round_count.toString())
            Log.d("kotlintest", "turnDataPlayer:" + turnDataPlayer)
            Log.d("kotlintest", "playerName:" + playerName)
            Log.d("kotlintest", "tableChips:" + tableChips.toString())
            Log.d("kotlintest", "turnDataChips:" + turnDataChips.toString() )
            Log.d("kotlintest", "playChips:" + playChips.toString())

            memberChangeChipsText.text = playChips.toString()
            playingCheck = "play"
            memberPlayingFoldText.text = ""
        }

        memberPlayingFoldButton.setOnClickListener {
            chipsNum1 = ""
            chipsNum2 = ""
            chipsNum3 = ""
            chipsNum4 = ""
            chipsNum5 = ""
            chipsNum6 = ""
            chipsNum7 = ""
            memberChangeChipsText.text = "0"
            playingCheck = "fold"

            memberPlayingFoldText.text = "fold"
        }

        memberPlayingAllInButton.setOnClickListener {
            chipsNum1 = ""
            chipsNum2 = ""
            chipsNum3 = ""
            chipsNum4 = ""
            chipsNum5 = ""
            chipsNum6 = ""
            chipsNum7 = ""
            playChips = turnChips
            memberChangeChipsText.text = turnChips.toString()
            playingCheck = "play"
            memberPlayingFoldText.text = ""
        }

        memberPlayingNumButton1.setOnClickListener {
            when {
                chipsNum1 == "" -> chipsNum1 = "1"
                chipsNum2 == "" -> chipsNum2 = "1"
                chipsNum3 == "" -> chipsNum3 = "1"
                chipsNum4 == "" -> chipsNum4 = "1"
                chipsNum5 == "" -> chipsNum5 = "1"
                chipsNum6 == "" -> chipsNum6 = "1"
                chipsNum7 == "" -> chipsNum7 = "1"


            }
            memberChangeChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            memberPlayingFoldText.text = ""
        }

        memberPlayingNumButton2.setOnClickListener {
            when {
                chipsNum1 == "" -> chipsNum1 = "2"
                chipsNum2 == "" -> chipsNum2 = "2"
                chipsNum3 == "" -> chipsNum3 = "2"
                chipsNum4 == "" -> chipsNum4 = "2"
                chipsNum5 == "" -> chipsNum5 = "2"
                chipsNum6 == "" -> chipsNum6 = "2"
                chipsNum7 == "" -> chipsNum7 = "2"


            }
            memberChangeChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            memberPlayingFoldText.text = ""
        }

        memberPlayingNumButton3.setOnClickListener {
            when {
                chipsNum1 == "" -> chipsNum1 = "3"
                chipsNum2 == "" -> chipsNum2 = "3"
                chipsNum3 == "" -> chipsNum3 = "3"
                chipsNum4 == "" -> chipsNum4 = "3"
                chipsNum5 == "" -> chipsNum5 = "3"
                chipsNum6 == "" -> chipsNum6 = "3"
                chipsNum7 == "" -> chipsNum7 = "3"


            }
            memberChangeChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            memberPlayingFoldText.text = ""
        }

        memberPlayingNumButton4.setOnClickListener {
            when {
                chipsNum1 == "" -> chipsNum1 = "4"
                chipsNum2 == "" -> chipsNum2 = "4"
                chipsNum3 == "" -> chipsNum3 = "4"
                chipsNum4 == "" -> chipsNum4 = "4"
                chipsNum5 == "" -> chipsNum5 = "4"
                chipsNum6 == "" -> chipsNum6 = "4"
                chipsNum7 == "" -> chipsNum7 = "4"


            }
            memberChangeChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            memberPlayingFoldText.text = ""
        }

        memberPlayingNumButton5.setOnClickListener {
            when {
                chipsNum1 == "" -> chipsNum1 = "5"
                chipsNum2 == "" -> chipsNum2 = "5"
                chipsNum3 == "" -> chipsNum3 = "5"
                chipsNum4 == "" -> chipsNum4 = "5"
                chipsNum5 == "" -> chipsNum5 = "5"
                chipsNum6 == "" -> chipsNum6 = "5"
                chipsNum7 == "" -> chipsNum7 = "5"


            }
            memberChangeChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            memberPlayingFoldText.text = ""
        }

        memberPlayingNumButton6.setOnClickListener {
            when {
                chipsNum1 == "" -> chipsNum1 = "6"
                chipsNum2 == "" -> chipsNum2 = "6"
                chipsNum3 == "" -> chipsNum3 = "6"
                chipsNum4 == "" -> chipsNum4 = "6"
                chipsNum5 == "" -> chipsNum5 = "6"
                chipsNum6 == "" -> chipsNum6 = "6"
                chipsNum7 == "" -> chipsNum7 = "6"


            }
            memberChangeChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            memberPlayingFoldText.text = ""
        }

        memberPlayingNumButton7.setOnClickListener {
            when {
                chipsNum1 == "" -> chipsNum1 = "7"
                chipsNum2 == "" -> chipsNum2 = "7"
                chipsNum3 == "" -> chipsNum3 = "7"
                chipsNum4 == "" -> chipsNum4 = "7"
                chipsNum5 == "" -> chipsNum5 = "7"
                chipsNum6 == "" -> chipsNum6 = "7"
                chipsNum7 == "" -> chipsNum7 = "7"

            }
            memberChangeChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            memberPlayingFoldText.text = ""
        }

        memberPlayingNumButton8.setOnClickListener {
            when {
                chipsNum1 == "" -> chipsNum1 = "8"
                chipsNum2 == "" -> chipsNum2 = "8"
                chipsNum3 == "" -> chipsNum3 = "8"
                chipsNum4 == "" -> chipsNum4 = "8"
                chipsNum5 == "" -> chipsNum5 = "8"
                chipsNum6 == "" -> chipsNum6 = "8"
                chipsNum7 == "" -> chipsNum7 = "8"


            }
            memberChangeChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            memberPlayingFoldText.text = ""
        }

        memberPlayingNumButton9.setOnClickListener {
            when {
                chipsNum1 == "" -> chipsNum1 = "9"
                chipsNum2 == "" -> chipsNum2 = "9"
                chipsNum3 == "" -> chipsNum3 = "9"
                chipsNum4 == "" -> chipsNum4 = "9"
                chipsNum5 == "" -> chipsNum5 = "9"
                chipsNum6 == "" -> chipsNum6 = "9"
                chipsNum7 == "" -> chipsNum7 = "9"


            }
            memberChangeChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            memberPlayingFoldText.text = ""
        }

        memberPlayingNumButton0.setOnClickListener {
            when {
                chipsNum2 == "" -> chipsNum2 = "0"
                chipsNum3 == "" -> chipsNum3 = "0"
                chipsNum4 == "" -> chipsNum4 = "0"
                chipsNum5 == "" -> chipsNum5 = "0"
                chipsNum6 == "" -> chipsNum6 = "0"
                chipsNum7 == "" -> chipsNum7 = "0"
            }
            memberChangeChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            memberPlayingFoldText.text = ""
        }

        memberPlayingNumButton00.setOnClickListener {
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
            memberChangeChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            memberPlayingFoldText.text = ""
        }

        memberPlayingNumButton000.setOnClickListener {
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
            memberChangeChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            memberPlayingFoldText.text = ""
        }

        memberPlayingNumButton0000.setOnClickListener {
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
            memberChangeChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            memberPlayingFoldText.text = ""
        }

        memberPalyingDeleteButton.setOnClickListener {
            chipsNum1 = "0"
            chipsNum2 = ""
            chipsNum3 = ""
            chipsNum4 = ""
            chipsNum5 = ""
            chipsNum6 = ""
            chipsNum7 = ""
            playingCheck = "fold"

            memberChangeChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            memberPlayingFoldText.text = ""
        }

        memberPlayingDoneButton.setOnClickListener {
            //チップ値が入力されているか確認
            if (memberChangeChipsText.text != "") {
                tableTotalChips = tableTotalChips + memberChangeChipsText.text.toString().toInt()

                //テーブルチップとの比較
                if (turnDataCount == count && turnDataRound == round && turnDataRoundCount + 1 == round_count && turnDataPlayer == playerName) {
                    when {
                        memberChangeChipsText.text.toString().toInt() == tableChips - turnDataChips -> {
                            if(playingCheck != "fold") {
                                sameChipsPlayer++
                            }
                        }
                        memberChangeChipsText.text.toString().toInt() > tableChips - turnDataChips -> {
                            sameChipsPlayer = 1
                            tableChips = memberChangeChipsText.text.toString().toInt()
                        }
                    }
                } else {
                    when {
                        memberChangeChipsText.text.toString().toInt() == tableChips -> {
                            if (playingCheck != "fold") {
                                sameChipsPlayer++
                            }
                        }
                        memberChangeChipsText.text.toString().toInt() > tableChips -> {
                            sameChipsPlayer = 1
                            tableChips = memberChangeChipsText.text.toString().toInt()
                        }
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
                    Log.d("kotlintest","Turn保存：" + identifier.toString())
                }

                mMember = Member()

                val memberRealmResults = mRealm.where(Member::class.java).findAll()
                //player_idを取得
                val memberPlayerNumRealmResults = mRealm.where(Member::class.java).equalTo("memberRound", playingNum).findAll()
                val playerNumId = memberPlayerNumRealmResults.max("id")!!.toInt()
                player_id = memberRealmResults[playerNumId]!!.member_id
                val playerTotalChips = memberRealmResults[playerNumId]!!.memberChips

                //Memberを更新
                var member = mRealm.where(Member::class.java).equalTo("id", playerNumId).findFirst()
                Log.d("kotlintest", "memberId:" + playerNumId.toString())

                //Turnを新規登録
                mTurn!!.game_id = game_id
                mTurn!!.count = count
                mTurn!!.round = round
                mTurn!!.round_count = round_count
                mTurn!!.player = playerName
                mTurn!!.player_id = player_id
                if (playingCheck == "fold" && round == "preflop" && round_count == 1) {
                    when (playingNum) {
                        preFlopNum -> {
                            Log.d("kotlintest", "登録MPA:SB")
                            mTurn!!.playChips = smallBlind
                            mTurn!!.memberChips = playerTotalChips - smallBlind
                            member!!.playChips = smallBlind
                            member!!.memberChips = playerTotalChips - smallBlind
                            tableTotalChips += smallBlind
                        }
                        bigBlindNum -> {
                            Log.d("kotlintest", "登録MPA:BB")
                            mTurn!!.playChips = bigBlind
                            mTurn!!.memberChips = playerTotalChips - bigBlind
                            member!!.playChips = bigBlind
                            member!!.memberChips = playerTotalChips - bigBlind
                            tableTotalChips += bigBlind
                        }
                        else -> {
                            Log.d("kotlintest", "登録MPA:else")
                            mTurn!!.playChips = 0
                            mTurn!!.memberChips = playerTotalChips
                            member!!.playChips = 0
                            member!!.memberChips = playerTotalChips

                        }
                    }
                } else {
                    Log.d("kotlintest", "登録MPA:play")
                    mTurn!!.playChips = memberChangeChipsText.text.toString().toInt()
                    mTurn!!.memberChips = playerTotalChips - memberChangeChipsText.text.toString().toInt()
                    member!!.playChips = memberChangeChipsText.text.toString().toInt()
                    member!!.memberChips = playerTotalChips - memberChangeChipsText.text.toString().toInt()
                }
                mTurn!!.tableChips = tableChips
                mTurn!!.tableTotalChips = tableTotalChips

                Log.d("kotlintest", "mTurn!!.game_id:" + mTurn!!.game_id.toString())
                Log.d("kotlintest", "mTurn!!.count:" + mTurn!!.count.toString())
                Log.d("kotlintest", "mTurn!!.round:" + mTurn!!.round)
                Log.d("kotlintest", "mTurn!!.round_count:" + mTurn!!.round_count.toString())
                Log.d("kotlintest", "mTurn!!.player:" + mTurn!!.player)
                Log.d("kotlintest", "mTurn!!.player_id:" + mTurn!!.player_id)
                Log.d("kotlintest", "mTurn!!.playChips:" + mTurn!!.playChips.toString())
                Log.d("kotlintest", "mTurn!!.memberChips:" + mTurn!!.memberChips.toString())
                Log.d("kotlintest", "mTurn!!.tableChips:" + mTurn!!.tableChips.toString())
                Log.d("kotlintest", "mTurn!!.tableTotalChips:" + mTurn!!.tableTotalChips.toString())

                if (playingCheck == "fold") {
                    member!!.playingCheck = "fold"
                    foldPlayer++
                }


                member!!.tableChips = tableChips
                member!!.tableTotalChips = tableTotalChips

                Log.d("kotlintest", "member!!.playChips:" + member!!.playChips.toString())
                Log.d("kotlintest", "member!!.memberChips:" + member!!.memberChips.toString())
                Log.d("kotlintest", "member!!.tableChips:" + member!!.tableChips.toString())
                Log.d("kotlintest", "member!!.tableTotalChips:" + member!!.tableTotalChips.toString())

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

                    //WinnerCheckAcitivtyへ移動
                    val intent = Intent(this@MemberPlayingActivity, WinnerCheckActivity::class.java)
                    Log.d("kotlintest", "通過MPA4")
                    Log.d("kotlintest", "MemberPlayingActivity -> WinnerCheckActivity[count]:" + count.toString())
                    Log.d("kotlintest", "MemberPlayingActivity -> WinnerCheckActivity[round]:" + round)
                    Log.d("kotlintest", "MemberPlayingActivity -> WinnerCheckActivity[round_count]:" + round_count.toString())
                    Log.d("kotlintest", "MemberPlayingActivity -> WinnerCheckActivity[roundNum]:" + roundNum.toString())
                    Log.d("kotlintest", "MemberPlayingActivity -> WinnerCheckActivity[myRound]:" + myRound.toString())
                    Log.d("kotlintest", "MemberPlayingActivity -> WinnerCheckActivity[flopNum]:" + flopNum.toString())
                    Log.d("kotlintest", "MemberPlayingActivity -> WinnerCheckActivity[preFlopNum]:" + preFlopNum.toString())
                    Log.d("kotlintest", "MemberPlayingActivity -> WinnerCheckActivity[bigBlindNum]" + bigBlindNum.toString())
                    Log.d("kotlintest", "MemberPlayingActivity -> WinnerCheckActivity[playingNum]:" + playingNum.toString())
                    Log.d("kotlintest", "MemberPlayingActivity -> WinnerCheckActivity[foldPlayer]:" + foldPlayer.toString())
                    Log.d("kotlintest", "MemberPlayingActivity -> WinnerCheckActivity[sameChipsPlayer]:" + sameChipsPlayer.toString())
                    Log.d("kotlintest", "MemberPlayingActivity -> WinnerCheckActivity[smallBlind]:" + smallBlind.toString())
                    Log.d("kotlintest", "MemberPlayingActivity -> WinnerCheckActivity[bigBlind]:" + bigBlind.toString())
                    Log.d("kotlintest", "MemberPlayingActivity -> WinnerCheckActivity[tableChips]:" + tableChips.toString())
                    Log.d("kotlintest", "MemberPlayingActivity -> WinnerCheckActivity[tableTotalChips]:" + tableTotalChips.toString())
                    Log.d("kotlintest", "MemberPlayingActivity -> WinnerCheckActivity[bigBlind]:" + bigBlind)
                    Log.d("kotlintest", "MemberPlayingActivity -> WinnerCheckActivity[smallBlind]:" + smallBlind)
                    Log.d("kotlintest", "MemberPlayingActivity -> WinnerCheckActivity[tableChips]:" + tableChips)
                    Log.d("kotlintest", "MemberPlayingActivity -> WinnerCheckActivity[tableTotalChips]:" + tableTotalChips)

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
                    intent.putExtra("bigBlindNum", bigBlindNum)
                    intent.putExtra("playingNum", playingNum)
                    intent.putExtra("foldPlayer", foldPlayer)
                    intent.putExtra("sameChipsPlayer", sameChipsPlayer)
                    intent.putExtra("firstRealm", firstRealm)
                    startActivity(intent)


                } else {
                    if (sameChipsPlayer >= memberNum - foldPlayer) {
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

                        //CardActivityへ移動

                        val intent =
                            Intent(this@MemberPlayingActivity, CardActivity::class.java)
                        Log.d("kotlintest", "通過MPA5")
                        Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[count]:" + count.toString())
                        Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[round]:" + round)
                        Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[round_count]:" + round_count.toString())
                        Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[roundNum]:" + roundNum.toString())
                        Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[myRound]:" + myRound.toString())
                        Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[flopNum]:" + flopNum.toString())
                        Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[preFlopNum]:" + preFlopNum.toString())
                        Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[bigBlindNum]" + bigBlindNum.toString())
                        Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[playingNum]:" + playingNum.toString())
                        Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[foldPlayer]:" + foldPlayer.toString())
                        Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[sameChipsPlayer]:" + sameChipsPlayer.toString())
                        Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[smallBlind]:" + smallBlind.toString())
                        Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[bigBlind]:" + bigBlind.toString())
                        Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[tableChips]:" + tableChips.toString())
                        Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[tableTotalChips]:" + tableTotalChips.toString())
                        Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[bigBlind]:" + bigBlind)
                        Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[smallBlind]:" + smallBlind)
                        Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[tableChips]:" + tableChips)
                        Log.d("kotlintest", "MemberPlayingActivity -> CardActivity[tableTotalChips]:" + tableTotalChips)

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
                        intent.putExtra("bigBlindNum", bigBlindNum)
                        intent.putExtra("playingNum", playingNum)
                        intent.putExtra("foldPlayer", foldPlayer)
                        intent.putExtra("sameChipsPlayer", sameChipsPlayer)
                        intent.putExtra("firstRealm", firstRealm)
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

                        val memberPlayerNumRealm =
                            mRealm.where(Member::class.java).equalTo("memberRound", playingNum)
                                .findAll()
                        val memberPlayerNumRealmId = memberPlayerNumRealm.max("id")!!.toInt()
                        val nextPlayerName = memberRealmResults[memberPlayerNumRealmId]!!.memberName

                        if (nextPlayerName == "自分") {
                            //PlayingActivityに移動
                            val intent =
                                Intent(this@MemberPlayingActivity, PlayingActivity::class.java)
                            Log.d("kotlintest", "通過MPA6")
                            Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[count]:" + count.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[round]:" + round)
                            Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[round_count]:" + round_count.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[roundNum]:" + roundNum.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[myRound]:" + myRound.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[flopNum]:" + flopNum.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[preFlopNum]:" + preFlopNum.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[bigBlindNum]" + bigBlindNum.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[playingNum]:" + playingNum.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[foldPlayer]:" + foldPlayer.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[sameChipsPlayer]:" + sameChipsPlayer.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[smallBlind]:" + smallBlind.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[bigBlind]:" + bigBlind.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[tableChips]:" + tableChips.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[tableTotalChips]:" + tableTotalChips.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[bigBlind]:" + bigBlind)
                            Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[smallBlind]:" + smallBlind)
                            Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[tableChips]:" + tableChips)
                            Log.d("kotlintest", "MemberPlayingActivity -> PlayingActivity[tableTotalChips]:" + tableTotalChips)

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
                            intent.putExtra("bigBlindNum", bigBlindNum)
                            intent.putExtra("playingNum", playingNum)
                            intent.putExtra("foldPlayer", foldPlayer)
                            intent.putExtra("sameChipsPlayer", sameChipsPlayer)
                            intent.putExtra("firstRealm", firstRealm)
                            startActivity(intent)
                        } else {
                            //MemberPlayingActivityに移動

                            val intent = Intent(
                                this@MemberPlayingActivity,
                                MemberPlayingActivity::class.java
                            )
                            Log.d("kotlintest", "通過MPA7")
                            Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[count]:" + count.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[round]:" + round)
                            Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[round_count]:" + round_count.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[roundNum]:" + roundNum.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[myRound]:" + myRound.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[flopNum]:" + flopNum.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[preFlopNum]:" + preFlopNum.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[bigBlindNum]" + bigBlindNum.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[playingNum]:" + playingNum.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[foldPlayer]:" + foldPlayer.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[sameChipsPlayer]:" + sameChipsPlayer.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[smallBlind]:" + smallBlind.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[bigBlind]:" + bigBlind.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[tableChips]:" + tableChips.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[tableTotalChips]:" + tableTotalChips.toString())
                            Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[bigBlind]:" + bigBlind)
                            Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[smallBlind]:" + smallBlind)
                            Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[tableChips]:" + tableChips)
                            Log.d("kotlintest", "MemberPlayingActivity -> MemberPlayingActivity[tableTotalChips]:" + tableTotalChips)


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
                            intent.putExtra("bigBlindNum", bigBlindNum)
                            intent.putExtra("playingNum", playingNum)
                            intent.putExtra("foldPlayer", foldPlayer)
                            intent.putExtra("sameChipsPlayer", sameChipsPlayer)
                            intent.putExtra("firstRealm", firstRealm)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()

        mRealm.close()
    }
}
