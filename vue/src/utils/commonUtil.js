import {ElMessage} from "element-plus";
import {ElLoading} from 'element-plus'
import axios from "axios";
const userInfo = {
    type:0,
    id:'2222',
    userName:'',
    studentNum:''
}
export default{
    messageBox,
    readFile,
    delay,
    userInfo,
    dateTranslate,
    loadingWait,
    axiosGet
}
function messageBox(msg, type) {
    ElMessage({
        showClose: false,
        message: msg,
        type: type,
        grouping: true
    })
}
function loadingWait(text)
{
    return ElLoading.service({
        lock:true,
        text:text,
        background:'rgba(0, 0, 0, 0.7)',
    })
}
export function readFile(file){
    return new Promise(resolve => {
        let reader = new FileReader()
        reader.readAsBinaryString(file)
        reader.onload =ev=>{
            resolve(ev.target.result)
        };
    });
}
function delay(interval =0){
    return new  Promise(resolve => {
        let timer = setTimeout(()=>{
            clearTimeout(timer)
            resolve()
        },interval);
    })
}
function dateTranslate(date)
{
    let year  = date.getFullYear()
    let Month = date.getMonth()+1
    let day = date.getDay()
    return year+'-'+Month+'-'+day
}
async function axiosGet(url)
{
    return  await axios.get(url)
        .then(res=>{
            console.log(res)
        })
        .catch(reason => {
            console.log(reason)
        })
}