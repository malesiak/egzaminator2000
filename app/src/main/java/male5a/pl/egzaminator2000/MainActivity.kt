package male5a.pl.egzaminator2000


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orm.SchemaGenerator
import com.orm.SugarContext
import com.orm.SugarDb
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cardexamview.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val myExamCrud = MyExamCrud()

    var examsList = ArrayList<ExamData>()


    private lateinit var adapterRecView: RecyclerAdapter

    private var myBtn: Button? = null

    override fun onClick(v: View?) {
        myBtnButtonClicked()
    }

    private fun myBtnButtonClicked() {
        Toast.makeText(applicationContext,myBtn?.text.toString(),Toast.LENGTH_SHORT).show()
        myBtn?.text = "dfgfgfg"
    }

    private fun initRecyclerView(){

        recview_exams.setHasFixedSize(true)

        recview_exams.layoutManager = LinearLayoutManager(this)

        recview_exams.itemAnimator = DefaultItemAnimator()

        recview_exams.adapter = adapterRecView

    }
    private fun Aktualizuj(){
        examsList.clear()
        val exams = myExamCrud.GetAllRecords()as ArrayList<ExamData>
        examsList.addAll(exams)
        lblListaEgzaminow.text=examsList.count().toString()

        adapterRecView.notifyDataSetChanged()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SugarContext.init(this)
        val schemaGenerator = SchemaGenerator(this)
        schemaGenerator.createDatabase(SugarDb(this).db)

        adapterRecView=RecyclerAdapter(examsList) { exam: ExamData, position: Int ->


            txtName.setText(exam.name.toString())
            txtDate.setText(exam.date.toString())
            txtTime.setText(exam.time.toString())


            myExamCrud.IDRecord=position
            lblID.text = myExamCrud.IDRecord.toString()
        }
        initRecyclerView()


        val ItemTouchHelperCallback=object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onSwiped(ViewHolder: RecyclerView.ViewHolder, position: Int) {

                myExamCrud.DeleteRecord(myExamCrud.IDRecord)

                examsList.clear()
                val exams = myExamCrud.GetAllRecords()as ArrayList<ExamData>
                examsList.addAll(exams)
                adapterRecView.notifyDataSetChanged() //aktualizacj recyclerview
            }

            override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                return false
            }

        }
        val itemTouchHelper= ItemTouchHelper(ItemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recview_exams)
        examsList.clear()
        val exams = myExamCrud.GetAllRecords()as ArrayList<ExamData>
        examsList.addAll(exams)
        lblListaEgzaminow.text=examsList.count().toString()

        adapterRecView.notifyDataSetChanged()


        btnAdd.setOnClickListener {
            myExamCrud.AddRecord(txtName.text.toString(),txtDate.text.toString(),txtTime.text.toString())
            adapterRecView.notifyDataSetChanged()

            Aktualizuj()
        }

        btnUpdate.setOnClickListener {
            myExamCrud.UpdateRecord(myExamCrud.IDRecord,txtName.text.toString(),txtDate.text.toString(),txtTime.text.toString())
            adapterRecView.notifyDataSetChanged()
        }

        btnShowData.setOnClickListener {

            Aktualizuj()
        }

        btnDelete.setOnClickListener {
            myExamCrud.DeleteRecord(myExamCrud.IDRecord)

            adapterRecView.notifyDataSetChanged()

            Aktualizuj()
        }
    }
}
