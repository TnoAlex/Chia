import { ElMessage } from 'element-plus'
function showMessage(message,type)
{
    ElMessage({
        message:message,
        type:type,
        grouping: true,
    })
}
export default {
    ShowMessage:showMessage
}
