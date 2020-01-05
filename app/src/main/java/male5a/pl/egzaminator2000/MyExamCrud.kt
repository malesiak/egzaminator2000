package male5a.pl.egzaminator2000

import com.orm.SugarRecord


class MyExamCrud {

    private var  idRecord:Int=-1

    public var IDRecord: Int
        get() = this.idRecord
        set(value) {
            idRecord=value
        }

    public fun AddRecord(name:String,date:String,time:String){
        if(name!="")
        {
            val exam =  ExamData()
            exam.name=name
            exam.date=date
            exam.time=time
            exam.save()
        }

    }

    public fun UpdateRecord(id:Int,name:String,date:String,time:String) {
        var exam =  ExamData()

        exam=SugarRecord.listAll(ExamData::class.java).get(id)
        exam.name=name
        exam.date=date
        exam.time=time
        exam.save()
    }

    public fun DeleteRecord(id:Int){
        var exam =  ExamData()
        exam=SugarRecord.listAll(ExamData::class.java).get(id)

        exam.delete()
    }
    public fun DeleteAllRecords(){
        SugarRecord.deleteAll(ExamData::class.java)
    }

    public fun GetAllRecords():List<ExamData>{

        val exams = SugarRecord.listAll(ExamData::class.java)
        return exams
    }

    public fun GetOneRecord():ExamData{

        val exam = SugarRecord.listAll(ExamData::class.java).get(0)
        return exam
    }

}