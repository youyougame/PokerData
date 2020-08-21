package jp.playing.table.pokerdata

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_playing.*

class DataDetailAdapter(context: Context) : BaseAdapter() {
    private val mLayoutInflater: LayoutInflater

    private lateinit var mRealm: Realm

    private var mMember: Member? = null

    private var mHand: Hand? = null

    private var mTurn: Turn? = null

    private var mPlayer: Player? = null

    private var hand1 = ""
    private var hand2 = ""
    private var table1 = ""
    private var table2 = ""
    private var table3 = ""
    private var table4 = ""
    private var table5 = ""

    private lateinit var hand1Image: ImageView
    private lateinit var hand2Image: ImageView
    private lateinit var table1Image: ImageView
    private lateinit var table2Image: ImageView
    private lateinit var table3Image: ImageView
    private lateinit var table4Image: ImageView
    private lateinit var table5Image: ImageView

    private var turnGameId = 0
    private var count = 0
    private var playerName = ""
    private var playerRound = 0
    private var round = ""


    init {
        this.mLayoutInflater = LayoutInflater.from(context)
    }

    var turnList = mutableListOf<Turn>()

    override fun getCount(): Int {
        return turnList.size
    }

    override fun getItem(position: Int): Any {
        return turnList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        mRealm = Realm.getDefaultInstance()
        mMember = Member()
        mHand = Hand()
        mTurn = Turn()
        mPlayer = Player()

        var convertView = convertView

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_data_detail, parent, false)
        }

        val playerNameText = convertView!!.findViewById<View>(R.id.dataDetailPlayerText) as TextView
        val roundText = convertView!!.findViewById<View>(R.id.dataDetailTableText) as TextView
        val playerTotalChipsText = convertView!!.findViewById<View>(R.id.dataDetailActionText) as TextView
        val playChipsText = convertView!!.findViewById<View>(R.id.dataDetailChipsText) as TextView
        val playGameText = convertView!!.findViewById<View>(R.id.dataDetailCountText) as TextView
        hand1Image = convertView!!.findViewById<View>(R.id.dataDetailHandCard1) as ImageView
        hand2Image = convertView!!.findViewById<View>(R.id.dataDetailHandCard2) as ImageView
        table1Image = convertView!!.findViewById<View>(R.id.dataDetailTableCard1) as ImageView
        table2Image = convertView!!.findViewById<View>(R.id.dataDetailTableCard2) as ImageView
        table3Image = convertView!!.findViewById<View>(R.id.dataDetailTableCard3) as ImageView
        table4Image = convertView!!.findViewById<View>(R.id.dataDetailTableCard4) as ImageView
        table5Image = convertView!!.findViewById<View>(R.id.dataDetailTableCard5) as ImageView

        hand1Image.setImageResource(R.drawable.card_back)
        hand2Image.setImageResource(R.drawable.card_back)
        table1Image.setImageResource(R.drawable.card_back)
        table2Image.setImageResource(R.drawable.card_back)
        table3Image.setImageResource(R.drawable.card_back)
        table4Image.setImageResource(R.drawable.card_back)
        table5Image.setImageResource(R.drawable.card_back)

        turnGameId = turnList[position].game_id
        count = turnList[position].count
        playerName = turnList[position].player
        playerRound = turnList[position].playerRound
        round = turnList[position].round
        val turnId = turnList[position].id
        Log.d("kotlintest", playerName)
        Log.d("kotlintest", playerRound.toString())

        var memberId = 0

        when (playerRound) {
            1 -> {
                val memberRealmResults = mRealm.where(Member::class.java).equalTo("game_id", turnGameId).and().equalTo("hand_count", count).and().equalTo("memberRound", 1.toInt()).findAll()
                memberId = memberRealmResults.max("id")!!.toInt()
            }

            2 -> {
                val memberRealmResults = mRealm.where(Member::class.java).equalTo("game_id", turnGameId).and().equalTo("hand_count", count).and().equalTo("memberRound", 2.toInt()).findAll()
                memberId = memberRealmResults.max("id")!!.toInt()
            }

            3 -> {
                val memberRealmResults = mRealm.where(Member::class.java).equalTo("game_id", turnGameId).and().equalTo("hand_count", count).and().equalTo("memberRound", 3.toInt()).findAll()
                memberId = memberRealmResults.max("id")!!.toInt()
            }

            4 -> {
                val memberRealmResults = mRealm.where(Member::class.java).equalTo("game_id", turnGameId).and().equalTo("hand_count", count).and().equalTo("memberRound", 4.toInt()).findAll()
                memberId = memberRealmResults.max("id")!!.toInt()
            }

            5 -> {
                val memberRealmResults = mRealm.where(Member::class.java).equalTo("game_id", turnGameId).and().equalTo("hand_count", count).and().equalTo("memberRound", 5.toInt()).findAll()
                memberId = memberRealmResults.max("id")!!.toInt()
            }

            6 -> {
                val memberRealmResults = mRealm.where(Member::class.java).equalTo("game_id", turnGameId).and().equalTo("hand_count", count).and().equalTo("memberRound", 6.toInt()).findAll()
                memberId = memberRealmResults.max("id")!!.toInt()
            }

            7 -> {
                val memberRealmResults = mRealm.where(Member::class.java).equalTo("game_id", turnGameId).and().equalTo("hand_count", count).and().equalTo("memberRound", 7.toInt()).findAll()
                memberId = memberRealmResults.max("id")!!.toInt()
            }

            8 -> {
                val memberRealmResults = mRealm.where(Member::class.java).equalTo("game_id", turnGameId).and().equalTo("hand_count", count).and().equalTo("memberRound", 8.toInt()).findAll()
                memberId = memberRealmResults.max("id")!!.toInt()
            }

            9 -> {
                val memberRealmResults = mRealm.where(Member::class.java).equalTo("game_id", turnGameId).and().equalTo("hand_count", count).and().equalTo("memberRound", 9.toInt()).findAll()
                memberId = memberRealmResults.max("id")!!.toInt()
            }

            10 -> {
                val memberRealmResults = mRealm.where(Member::class.java).equalTo("game_id", turnGameId).and().equalTo("hand_count", count).and().equalTo("memberRound", 10.toInt()).findAll()
                memberId = memberRealmResults.max("id")!!.toInt()
            }

        }
        val memberData = mRealm.where(Member::class.java).equalTo("id", memberId).findFirst()
        hand1 = memberData!!.hand1
        hand2 = memberData!!.hand2


        playerNameText.text = turnList[position].player
        roundText.text = turnList[position].round
        playerTotalChipsText.text ="所持チップ:" + turnList[position].memberChips.toString()
        playChipsText.text ="賭けたチップ:" + turnList[position].playChips.toString()
        playGameText.text = count.toString() + "ゲーム"
        handSetting1()
        handSetting2()
        if (playerRound != 0) {
            tableCardSetting()
        }

        mRealm.close()

        return convertView
    }

    private fun handSetting1() {
        when (hand1){
            "club1" -> hand1Image.setImageResource(R.drawable.club1)
            "club2" -> hand1Image.setImageResource(R.drawable.club2)
            "club3" -> hand1Image.setImageResource(R.drawable.club3)
            "club4" -> hand1Image.setImageResource(R.drawable.club4)
            "club5" -> hand1Image.setImageResource(R.drawable.club5)
            "club6" -> hand1Image.setImageResource(R.drawable.club6)
            "club7" -> hand1Image.setImageResource(R.drawable.club7)
            "club8" -> hand1Image.setImageResource(R.drawable.club8)
            "club9" -> hand1Image.setImageResource(R.drawable.club9)
            "club10" -> hand1Image.setImageResource(R.drawable.club10)
            "club11" -> hand1Image.setImageResource(R.drawable.club11)
            "club12" -> hand1Image.setImageResource(R.drawable.club12)
            "club13" -> hand1Image.setImageResource(R.drawable.club13)

            "diamond1" -> hand1Image.setImageResource(R.drawable.diamond1)
            "diamond2" -> hand1Image.setImageResource(R.drawable.diamond2)
            "diamond3" -> hand1Image.setImageResource(R.drawable.diamond3)
            "diamond4" -> hand1Image.setImageResource(R.drawable.diamond4)
            "diamond5" -> hand1Image.setImageResource(R.drawable.diamond5)
            "diamond6" -> hand1Image.setImageResource(R.drawable.diamond6)
            "diamond7" -> hand1Image.setImageResource(R.drawable.diamond7)
            "diamond8" -> hand1Image.setImageResource(R.drawable.diamond8)
            "diamond9" -> hand1Image.setImageResource(R.drawable.diamond9)
            "diamond10" -> hand1Image.setImageResource(R.drawable.diamond10)
            "diamond11" -> hand1Image.setImageResource(R.drawable.diamond11)
            "diamond12" -> hand1Image.setImageResource(R.drawable.diamond12)
            "diamond13" -> hand1Image.setImageResource(R.drawable.diamond13)

            "heart1" -> hand1Image.setImageResource(R.drawable.heart1)
            "heart2" -> hand1Image.setImageResource(R.drawable.heart2)
            "heart3" -> hand1Image.setImageResource(R.drawable.heart3)
            "heart4" -> hand1Image.setImageResource(R.drawable.heart4)
            "heart5" -> hand1Image.setImageResource(R.drawable.heart5)
            "heart6" -> hand1Image.setImageResource(R.drawable.heart6)
            "heart7" -> hand1Image.setImageResource(R.drawable.heart7)
            "heart8" -> hand1Image.setImageResource(R.drawable.heart8)
            "heart9" -> hand1Image.setImageResource(R.drawable.heart9)
            "heart10" -> hand1Image.setImageResource(R.drawable.heart10)
            "heart11" -> hand1Image.setImageResource(R.drawable.heart11)
            "heart12" -> hand1Image.setImageResource(R.drawable.heart12)
            "heart13" -> hand1Image.setImageResource(R.drawable.heart13)

            "spade1" -> hand1Image.setImageResource(R.drawable.spade1)
            "spade2" -> hand1Image.setImageResource(R.drawable.spade2)
            "spade3" -> hand1Image.setImageResource(R.drawable.spade3)
            "spade4" -> hand1Image.setImageResource(R.drawable.spade4)
            "spade5" -> hand1Image.setImageResource(R.drawable.spade5)
            "spade6" -> hand1Image.setImageResource(R.drawable.spade6)
            "spade7" -> hand1Image.setImageResource(R.drawable.spade7)
            "spade8" -> hand1Image.setImageResource(R.drawable.spade8)
            "spade9" -> hand1Image.setImageResource(R.drawable.spade9)
            "spade10" -> hand1Image.setImageResource(R.drawable.spade10)
            "spade11" -> hand1Image.setImageResource(R.drawable.spade11)
            "spade12" -> hand1Image.setImageResource(R.drawable.spade12)
            "spade13" -> hand1Image.setImageResource(R.drawable.spade13)

            else -> hand1Image.setImageResource(R.drawable.card_back)
        }
    }

    private fun handSetting2() {
        when (hand2){
            "club1" -> hand2Image.setImageResource(R.drawable.club1)
            "club2" -> hand2Image.setImageResource(R.drawable.club2)
            "club3" -> hand2Image.setImageResource(R.drawable.club3)
            "club4" -> hand2Image.setImageResource(R.drawable.club4)
            "club5" -> hand2Image.setImageResource(R.drawable.club5)
            "club6" -> hand2Image.setImageResource(R.drawable.club6)
            "club7" -> hand2Image.setImageResource(R.drawable.club7)
            "club8" -> hand2Image.setImageResource(R.drawable.club8)
            "club9" -> hand2Image.setImageResource(R.drawable.club9)
            "club10" -> hand2Image.setImageResource(R.drawable.club10)
            "club11" -> hand2Image.setImageResource(R.drawable.club11)
            "club12" -> hand2Image.setImageResource(R.drawable.club12)
            "club13" -> hand2Image.setImageResource(R.drawable.club13)

            "diamond1" -> hand2Image.setImageResource(R.drawable.diamond1)
            "diamond2" -> hand2Image.setImageResource(R.drawable.diamond2)
            "diamond3" -> hand2Image.setImageResource(R.drawable.diamond3)
            "diamond4" -> hand2Image.setImageResource(R.drawable.diamond4)
            "diamond5" -> hand2Image.setImageResource(R.drawable.diamond5)
            "diamond6" -> hand2Image.setImageResource(R.drawable.diamond6)
            "diamond7" -> hand2Image.setImageResource(R.drawable.diamond7)
            "diamond8" -> hand2Image.setImageResource(R.drawable.diamond8)
            "diamond9" -> hand2Image.setImageResource(R.drawable.diamond9)
            "diamond10" -> hand2Image.setImageResource(R.drawable.diamond10)
            "diamond11" -> hand2Image.setImageResource(R.drawable.diamond11)
            "diamond12" -> hand2Image.setImageResource(R.drawable.diamond12)
            "diamond13" -> hand2Image.setImageResource(R.drawable.diamond13)

            "heart1" -> hand2Image.setImageResource(R.drawable.heart1)
            "heart2" -> hand2Image.setImageResource(R.drawable.heart2)
            "heart3" -> hand2Image.setImageResource(R.drawable.heart3)
            "heart4" -> hand2Image.setImageResource(R.drawable.heart4)
            "heart5" -> hand2Image.setImageResource(R.drawable.heart5)
            "heart6" -> hand2Image.setImageResource(R.drawable.heart6)
            "heart7" -> hand2Image.setImageResource(R.drawable.heart7)
            "heart8" -> hand2Image.setImageResource(R.drawable.heart8)
            "heart9" -> hand2Image.setImageResource(R.drawable.heart9)
            "heart10" -> hand2Image.setImageResource(R.drawable.heart10)
            "heart11" -> hand2Image.setImageResource(R.drawable.heart11)
            "heart12" -> hand2Image.setImageResource(R.drawable.heart12)
            "heart13" -> hand2Image.setImageResource(R.drawable.heart13)

            "spade1" -> hand2Image.setImageResource(R.drawable.spade1)
            "spade2" -> hand2Image.setImageResource(R.drawable.spade2)
            "spade3" -> hand2Image.setImageResource(R.drawable.spade3)
            "spade4" -> hand2Image.setImageResource(R.drawable.spade4)
            "spade5" -> hand2Image.setImageResource(R.drawable.spade5)
            "spade6" -> hand2Image.setImageResource(R.drawable.spade6)
            "spade7" -> hand2Image.setImageResource(R.drawable.spade7)
            "spade8" -> hand2Image.setImageResource(R.drawable.spade8)
            "spade9" -> hand2Image.setImageResource(R.drawable.spade9)
            "spade10" -> hand2Image.setImageResource(R.drawable.spade10)
            "spade11" -> hand2Image.setImageResource(R.drawable.spade11)
            "spade12" -> hand2Image.setImageResource(R.drawable.spade12)
            "spade13" -> hand2Image.setImageResource(R.drawable.spade13)

            else -> hand2Image.setImageResource(R.drawable.card_back)
        }
    }

    private fun tableSetting1() {
        when (table1){
            "club1" -> table1Image.setImageResource(R.drawable.club1)
            "club2" -> table1Image.setImageResource(R.drawable.club2)
            "club3" -> table1Image.setImageResource(R.drawable.club3)
            "club4" -> table1Image.setImageResource(R.drawable.club4)
            "club5" -> table1Image.setImageResource(R.drawable.club5)
            "club6" -> table1Image.setImageResource(R.drawable.club6)
            "club7" -> table1Image.setImageResource(R.drawable.club7)
            "club8" -> table1Image.setImageResource(R.drawable.club8)
            "club9" -> table1Image.setImageResource(R.drawable.club9)
            "club10" -> table1Image.setImageResource(R.drawable.club10)
            "club11" -> table1Image.setImageResource(R.drawable.club11)
            "club12" -> table1Image.setImageResource(R.drawable.club12)
            "club13" -> table1Image.setImageResource(R.drawable.club13)

            "diamond1" -> table1Image.setImageResource(R.drawable.diamond1)
            "diamond2" -> table1Image.setImageResource(R.drawable.diamond2)
            "diamond3" -> table1Image.setImageResource(R.drawable.diamond3)
            "diamond4" -> table1Image.setImageResource(R.drawable.diamond4)
            "diamond5" -> table1Image.setImageResource(R.drawable.diamond5)
            "diamond6" -> table1Image.setImageResource(R.drawable.diamond6)
            "diamond7" -> table1Image.setImageResource(R.drawable.diamond7)
            "diamond8" -> table1Image.setImageResource(R.drawable.diamond8)
            "diamond9" -> table1Image.setImageResource(R.drawable.diamond9)
            "diamond10" -> table1Image.setImageResource(R.drawable.diamond10)
            "diamond11" -> table1Image.setImageResource(R.drawable.diamond11)
            "diamond12" -> table1Image.setImageResource(R.drawable.diamond12)
            "diamond13" -> table1Image.setImageResource(R.drawable.diamond13)

            "heart1" -> table1Image.setImageResource(R.drawable.heart1)
            "heart2" -> table1Image.setImageResource(R.drawable.heart2)
            "heart3" -> table1Image.setImageResource(R.drawable.heart3)
            "heart4" -> table1Image.setImageResource(R.drawable.heart4)
            "heart5" -> table1Image.setImageResource(R.drawable.heart5)
            "heart6" -> table1Image.setImageResource(R.drawable.heart6)
            "heart7" -> table1Image.setImageResource(R.drawable.heart7)
            "heart8" -> table1Image.setImageResource(R.drawable.heart8)
            "heart9" -> table1Image.setImageResource(R.drawable.heart9)
            "heart10" -> table1Image.setImageResource(R.drawable.heart10)
            "heart11" -> table1Image.setImageResource(R.drawable.heart11)
            "heart12" -> table1Image.setImageResource(R.drawable.heart12)
            "heart13" -> table1Image.setImageResource(R.drawable.heart13)

            "spade1" -> table1Image.setImageResource(R.drawable.spade1)
            "spade2" -> table1Image.setImageResource(R.drawable.spade2)
            "spade3" -> table1Image.setImageResource(R.drawable.spade3)
            "spade4" -> table1Image.setImageResource(R.drawable.spade4)
            "spade5" -> table1Image.setImageResource(R.drawable.spade5)
            "spade6" -> table1Image.setImageResource(R.drawable.spade6)
            "spade7" -> table1Image.setImageResource(R.drawable.spade7)
            "spade8" -> table1Image.setImageResource(R.drawable.spade8)
            "spade9" -> table1Image.setImageResource(R.drawable.spade9)
            "spade10" -> table1Image.setImageResource(R.drawable.spade10)
            "spade11" -> table1Image.setImageResource(R.drawable.spade11)
            "spade12" -> table1Image.setImageResource(R.drawable.spade12)
            "spade13" -> table1Image.setImageResource(R.drawable.spade13)

            else -> table1Image.setImageResource(R.drawable.card_back)
        }
    }

    private fun tableSetting2() {
        when (table2){
            "club1" -> table2Image.setImageResource(R.drawable.club1)
            "club2" -> table2Image.setImageResource(R.drawable.club2)
            "club3" -> table2Image.setImageResource(R.drawable.club3)
            "club4" -> table2Image.setImageResource(R.drawable.club4)
            "club5" -> table2Image.setImageResource(R.drawable.club5)
            "club6" -> table2Image.setImageResource(R.drawable.club6)
            "club7" -> table2Image.setImageResource(R.drawable.club7)
            "club8" -> table2Image.setImageResource(R.drawable.club8)
            "club9" -> table2Image.setImageResource(R.drawable.club9)
            "club10" -> table2Image.setImageResource(R.drawable.club10)
            "club11" -> table2Image.setImageResource(R.drawable.club11)
            "club12" -> table2Image.setImageResource(R.drawable.club12)
            "club13" -> table2Image.setImageResource(R.drawable.club13)

            "diamond1" -> table2Image.setImageResource(R.drawable.diamond1)
            "diamond2" -> table2Image.setImageResource(R.drawable.diamond2)
            "diamond3" -> table2Image.setImageResource(R.drawable.diamond3)
            "diamond4" -> table2Image.setImageResource(R.drawable.diamond4)
            "diamond5" -> table2Image.setImageResource(R.drawable.diamond5)
            "diamond6" -> table2Image.setImageResource(R.drawable.diamond6)
            "diamond7" -> table2Image.setImageResource(R.drawable.diamond7)
            "diamond8" -> table2Image.setImageResource(R.drawable.diamond8)
            "diamond9" -> table2Image.setImageResource(R.drawable.diamond9)
            "diamond10" -> table2Image.setImageResource(R.drawable.diamond10)
            "diamond11" -> table2Image.setImageResource(R.drawable.diamond11)
            "diamond12" -> table2Image.setImageResource(R.drawable.diamond12)
            "diamond13" -> table2Image.setImageResource(R.drawable.diamond13)

            "heart1" -> table2Image.setImageResource(R.drawable.heart1)
            "heart2" -> table2Image.setImageResource(R.drawable.heart2)
            "heart3" -> table2Image.setImageResource(R.drawable.heart3)
            "heart4" -> table2Image.setImageResource(R.drawable.heart4)
            "heart5" -> table2Image.setImageResource(R.drawable.heart5)
            "heart6" -> table2Image.setImageResource(R.drawable.heart6)
            "heart7" -> table2Image.setImageResource(R.drawable.heart7)
            "heart8" -> table2Image.setImageResource(R.drawable.heart8)
            "heart9" -> table2Image.setImageResource(R.drawable.heart9)
            "heart10" -> table2Image.setImageResource(R.drawable.heart10)
            "heart11" -> table2Image.setImageResource(R.drawable.heart11)
            "heart12" -> table2Image.setImageResource(R.drawable.heart12)
            "heart13" -> table2Image.setImageResource(R.drawable.heart13)

            "spade1" -> table2Image.setImageResource(R.drawable.spade1)
            "spade2" -> table2Image.setImageResource(R.drawable.spade2)
            "spade3" -> table2Image.setImageResource(R.drawable.spade3)
            "spade4" -> table2Image.setImageResource(R.drawable.spade4)
            "spade5" -> table2Image.setImageResource(R.drawable.spade5)
            "spade6" -> table2Image.setImageResource(R.drawable.spade6)
            "spade7" -> table2Image.setImageResource(R.drawable.spade7)
            "spade8" -> table2Image.setImageResource(R.drawable.spade8)
            "spade9" -> table2Image.setImageResource(R.drawable.spade9)
            "spade10" -> table2Image.setImageResource(R.drawable.spade10)
            "spade11" -> table2Image.setImageResource(R.drawable.spade11)
            "spade12" -> table2Image.setImageResource(R.drawable.spade12)
            "spade13" -> table2Image.setImageResource(R.drawable.spade13)

            else -> table2Image.setImageResource(R.drawable.card_back)
        }
    }

    private fun tableSetting3() {
        when (table3){
            "club1" -> table3Image.setImageResource(R.drawable.club1)
            "club2" -> table3Image.setImageResource(R.drawable.club2)
            "club3" -> table3Image.setImageResource(R.drawable.club3)
            "club4" -> table3Image.setImageResource(R.drawable.club4)
            "club5" -> table3Image.setImageResource(R.drawable.club5)
            "club6" -> table3Image.setImageResource(R.drawable.club6)
            "club7" -> table3Image.setImageResource(R.drawable.club7)
            "club8" -> table3Image.setImageResource(R.drawable.club8)
            "club9" -> table3Image.setImageResource(R.drawable.club9)
            "club10" -> table3Image.setImageResource(R.drawable.club10)
            "club11" -> table3Image.setImageResource(R.drawable.club11)
            "club12" -> table3Image.setImageResource(R.drawable.club12)
            "club13" -> table3Image.setImageResource(R.drawable.club13)

            "diamond1" -> table3Image.setImageResource(R.drawable.diamond1)
            "diamond2" -> table3Image.setImageResource(R.drawable.diamond2)
            "diamond3" -> table3Image.setImageResource(R.drawable.diamond3)
            "diamond4" -> table3Image.setImageResource(R.drawable.diamond4)
            "diamond5" -> table3Image.setImageResource(R.drawable.diamond5)
            "diamond6" -> table3Image.setImageResource(R.drawable.diamond6)
            "diamond7" -> table3Image.setImageResource(R.drawable.diamond7)
            "diamond8" -> table3Image.setImageResource(R.drawable.diamond8)
            "diamond9" -> table3Image.setImageResource(R.drawable.diamond9)
            "diamond10" -> table3Image.setImageResource(R.drawable.diamond10)
            "diamond11" -> table3Image.setImageResource(R.drawable.diamond11)
            "diamond12" -> table3Image.setImageResource(R.drawable.diamond12)
            "diamond13" -> table3Image.setImageResource(R.drawable.diamond13)

            "heart1" -> table3Image.setImageResource(R.drawable.heart1)
            "heart2" -> table3Image.setImageResource(R.drawable.heart2)
            "heart3" -> table3Image.setImageResource(R.drawable.heart3)
            "heart4" -> table3Image.setImageResource(R.drawable.heart4)
            "heart5" -> table3Image.setImageResource(R.drawable.heart5)
            "heart6" -> table3Image.setImageResource(R.drawable.heart6)
            "heart7" -> table3Image.setImageResource(R.drawable.heart7)
            "heart8" -> table3Image.setImageResource(R.drawable.heart8)
            "heart9" -> table3Image.setImageResource(R.drawable.heart9)
            "heart10" -> table3Image.setImageResource(R.drawable.heart10)
            "heart11" -> table3Image.setImageResource(R.drawable.heart11)
            "heart12" -> table3Image.setImageResource(R.drawable.heart12)
            "heart13" -> table3Image.setImageResource(R.drawable.heart13)

            "spade1" -> table3Image.setImageResource(R.drawable.spade1)
            "spade2" -> table3Image.setImageResource(R.drawable.spade2)
            "spade3" -> table3Image.setImageResource(R.drawable.spade3)
            "spade4" -> table3Image.setImageResource(R.drawable.spade4)
            "spade5" -> table3Image.setImageResource(R.drawable.spade5)
            "spade6" -> table3Image.setImageResource(R.drawable.spade6)
            "spade7" -> table3Image.setImageResource(R.drawable.spade7)
            "spade8" -> table3Image.setImageResource(R.drawable.spade8)
            "spade9" -> table3Image.setImageResource(R.drawable.spade9)
            "spade10" -> table3Image.setImageResource(R.drawable.spade10)
            "spade11" -> table3Image.setImageResource(R.drawable.spade11)
            "spade12" -> table3Image.setImageResource(R.drawable.spade12)
            "spade13" -> table3Image.setImageResource(R.drawable.spade13)

            else -> table3Image.setImageResource(R.drawable.card_back)
        }
    }

    private fun tableSetting4() {
        when (table4){
            "club1" -> table4Image.setImageResource(R.drawable.club1)
            "club2" -> table4Image.setImageResource(R.drawable.club2)
            "club3" -> table4Image.setImageResource(R.drawable.club3)
            "club4" -> table4Image.setImageResource(R.drawable.club4)
            "club5" -> table4Image.setImageResource(R.drawable.club5)
            "club6" -> table4Image.setImageResource(R.drawable.club6)
            "club7" -> table4Image.setImageResource(R.drawable.club7)
            "club8" -> table4Image.setImageResource(R.drawable.club8)
            "club9" -> table4Image.setImageResource(R.drawable.club9)
            "club10" -> table4Image.setImageResource(R.drawable.club10)
            "club11" -> table4Image.setImageResource(R.drawable.club11)
            "club12" -> table4Image.setImageResource(R.drawable.club12)
            "club13" -> table4Image.setImageResource(R.drawable.club13)

            "diamond1" -> table4Image.setImageResource(R.drawable.diamond1)
            "diamond2" -> table4Image.setImageResource(R.drawable.diamond2)
            "diamond3" -> table4Image.setImageResource(R.drawable.diamond3)
            "diamond4" -> table4Image.setImageResource(R.drawable.diamond4)
            "diamond5" -> table4Image.setImageResource(R.drawable.diamond5)
            "diamond6" -> table4Image.setImageResource(R.drawable.diamond6)
            "diamond7" -> table4Image.setImageResource(R.drawable.diamond7)
            "diamond8" -> table4Image.setImageResource(R.drawable.diamond8)
            "diamond9" -> table4Image.setImageResource(R.drawable.diamond9)
            "diamond10" -> table4Image.setImageResource(R.drawable.diamond10)
            "diamond11" -> table4Image.setImageResource(R.drawable.diamond11)
            "diamond12" -> table4Image.setImageResource(R.drawable.diamond12)
            "diamond13" -> table4Image.setImageResource(R.drawable.diamond13)

            "heart1" -> table4Image.setImageResource(R.drawable.heart1)
            "heart2" -> table4Image.setImageResource(R.drawable.heart2)
            "heart3" -> table4Image.setImageResource(R.drawable.heart3)
            "heart4" -> table4Image.setImageResource(R.drawable.heart4)
            "heart5" -> table4Image.setImageResource(R.drawable.heart5)
            "heart6" -> table4Image.setImageResource(R.drawable.heart6)
            "heart7" -> table4Image.setImageResource(R.drawable.heart7)
            "heart8" -> table4Image.setImageResource(R.drawable.heart8)
            "heart9" -> table4Image.setImageResource(R.drawable.heart9)
            "heart10" -> table4Image.setImageResource(R.drawable.heart10)
            "heart11" -> table4Image.setImageResource(R.drawable.heart11)
            "heart12" -> table4Image.setImageResource(R.drawable.heart12)
            "heart13" -> table4Image.setImageResource(R.drawable.heart13)

            "spade1" -> table4Image.setImageResource(R.drawable.spade1)
            "spade2" -> table4Image.setImageResource(R.drawable.spade2)
            "spade3" -> table4Image.setImageResource(R.drawable.spade3)
            "spade4" -> table4Image.setImageResource(R.drawable.spade4)
            "spade5" -> table4Image.setImageResource(R.drawable.spade5)
            "spade6" -> table4Image.setImageResource(R.drawable.spade6)
            "spade7" -> table4Image.setImageResource(R.drawable.spade7)
            "spade8" -> table4Image.setImageResource(R.drawable.spade8)
            "spade9" -> table4Image.setImageResource(R.drawable.spade9)
            "spade10" -> table4Image.setImageResource(R.drawable.spade10)
            "spade11" -> table4Image.setImageResource(R.drawable.spade11)
            "spade12" -> table4Image.setImageResource(R.drawable.spade12)
            "spade13" -> table4Image.setImageResource(R.drawable.spade13)

            else -> table4Image.setImageResource(R.drawable.card_back)
        }
    }

    private fun tableSetting5() {
        when (table5){
            "club1" -> table5Image.setImageResource(R.drawable.club1)
            "club2" -> table5Image.setImageResource(R.drawable.club2)
            "club3" -> table5Image.setImageResource(R.drawable.club3)
            "club4" -> table5Image.setImageResource(R.drawable.club4)
            "club5" -> table5Image.setImageResource(R.drawable.club5)
            "club6" -> table5Image.setImageResource(R.drawable.club6)
            "club7" -> table5Image.setImageResource(R.drawable.club7)
            "club8" -> table5Image.setImageResource(R.drawable.club8)
            "club9" -> table5Image.setImageResource(R.drawable.club9)
            "club10" -> table5Image.setImageResource(R.drawable.club10)
            "club11" -> table5Image.setImageResource(R.drawable.club11)
            "club12" -> table5Image.setImageResource(R.drawable.club12)
            "club13" -> table5Image.setImageResource(R.drawable.club13)

            "diamond1" -> table5Image.setImageResource(R.drawable.diamond1)
            "diamond2" -> table5Image.setImageResource(R.drawable.diamond2)
            "diamond3" -> table5Image.setImageResource(R.drawable.diamond3)
            "diamond4" -> table5Image.setImageResource(R.drawable.diamond4)
            "diamond5" -> table5Image.setImageResource(R.drawable.diamond5)
            "diamond6" -> table5Image.setImageResource(R.drawable.diamond6)
            "diamond7" -> table5Image.setImageResource(R.drawable.diamond7)
            "diamond8" -> table5Image.setImageResource(R.drawable.diamond8)
            "diamond9" -> table5Image.setImageResource(R.drawable.diamond9)
            "diamond10" -> table5Image.setImageResource(R.drawable.diamond10)
            "diamond11" -> table5Image.setImageResource(R.drawable.diamond11)
            "diamond12" -> table5Image.setImageResource(R.drawable.diamond12)
            "diamond13" -> table5Image.setImageResource(R.drawable.diamond13)

            "heart1" -> table5Image.setImageResource(R.drawable.heart1)
            "heart2" -> table5Image.setImageResource(R.drawable.heart2)
            "heart3" -> table5Image.setImageResource(R.drawable.heart3)
            "heart4" -> table5Image.setImageResource(R.drawable.heart4)
            "heart5" -> table5Image.setImageResource(R.drawable.heart5)
            "heart6" -> table5Image.setImageResource(R.drawable.heart6)
            "heart7" -> table5Image.setImageResource(R.drawable.heart7)
            "heart8" -> table5Image.setImageResource(R.drawable.heart8)
            "heart9" -> table5Image.setImageResource(R.drawable.heart9)
            "heart10" -> table5Image.setImageResource(R.drawable.heart10)
            "heart11" -> table5Image.setImageResource(R.drawable.heart11)
            "heart12" -> table5Image.setImageResource(R.drawable.heart12)
            "heart13" -> table5Image.setImageResource(R.drawable.heart13)

            "spade1" -> table5Image.setImageResource(R.drawable.spade1)
            "spade2" -> table5Image.setImageResource(R.drawable.spade2)
            "spade3" -> table5Image.setImageResource(R.drawable.spade3)
            "spade4" -> table5Image.setImageResource(R.drawable.spade4)
            "spade5" -> table5Image.setImageResource(R.drawable.spade5)
            "spade6" -> table5Image.setImageResource(R.drawable.spade6)
            "spade7" -> table5Image.setImageResource(R.drawable.spade7)
            "spade8" -> table5Image.setImageResource(R.drawable.spade8)
            "spade9" -> table5Image.setImageResource(R.drawable.spade9)
            "spade10" -> table5Image.setImageResource(R.drawable.spade10)
            "spade11" -> table5Image.setImageResource(R.drawable.spade11)
            "spade12" -> table5Image.setImageResource(R.drawable.spade12)
            "spade13" -> table5Image.setImageResource(R.drawable.spade13)

            else -> table5Image.setImageResource(R.drawable.card_back)
        }
    }

    private fun tableCardSetting() {
        val handRealmResults = mRealm.where(Hand::class.java).equalTo("game_id", turnGameId).and().equalTo("count", count).findAll()
        Log.d("kotlintest", "データ：" + handRealmResults.toString())
        Log.d("kotlintest", "数字：" + count.toString())
        val handId = handRealmResults.max("id")!!.toInt()
        val handData = mRealm.where(Hand::class.java).equalTo("id", handId).findFirst()

        table1 = handData!!.table1
        table2 = handData!!.table2
        table3 = handData!!.table3
        table4 = handData!!.table4
        table5 = handData!!.table5

        when(round) {
            "preflop" -> {
                table1Image.setImageResource(R.drawable.card_back)
                table2Image.setImageResource(R.drawable.card_back)
                table3Image.setImageResource(R.drawable.card_back)
                table4Image.setImageResource(R.drawable.card_back)
                table5Image.setImageResource(R.drawable.card_back)
            }
            "flop" -> {
                tableSetting1()
                tableSetting2()
                tableSetting3()
                table4Image.setImageResource(R.drawable.card_back)
                table5Image.setImageResource(R.drawable.card_back)

            }
            "turn" -> {
                tableSetting1()
                tableSetting2()
                tableSetting3()
                tableSetting4()
                table5Image.setImageResource(R.drawable.card_back)
            }
            "river" -> {
                tableSetting1()
                tableSetting2()
                tableSetting3()
                tableSetting4()
                tableSetting5()
            }
        }

    }

}
