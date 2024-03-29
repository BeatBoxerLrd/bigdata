package com.derry.mr;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

/**
 * @Author:derry
 * @Description:
 * @Date: Created in 18:49 2021/7/18
 * @Modified By:
 */
public class FlowCountReducer extends Reducer<Text, FlowBean, Text, FlowBean> {
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        //上传和下载的总和初始化
        long sum_upFlow = 0;
        long sum_downFlow = 0;

        // 1 遍历所用bean，将其中的上行流量，下行流量分别累加
        for (FlowBean flowBean : values) {
            //所有的上传的流量加在一起
            sum_upFlow += flowBean.getUpFlow();
            //所有的下载的流量加在一起
            sum_downFlow += flowBean.getDownFlow();
        }
        // 2 封装对象
        FlowBean resultBean = new FlowBean(sum_upFlow, sum_downFlow);
        // 3 写出
        context.write(key, resultBean);
    }
}
