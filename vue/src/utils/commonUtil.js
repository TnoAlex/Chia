import {ElMessage} from "element-plus";

export default function messageBox(msg, type) {
    ElMessage({
        showClose: false,
        message: msg,
        type: type,
        grouping: true
    })
}