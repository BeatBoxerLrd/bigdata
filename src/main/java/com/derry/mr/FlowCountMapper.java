package com.derry.mr;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author:derry
 * @Description:
 * @Date: Created in 18:40 2021/7/18
 * @Modified By:
 */
public class FlowCountMapper extends Mapper<LongWritable, Text, Text, FlowBean> {
    Text k = new Text();
    FlowBean v = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取每一行数据
        String line = value.toString();

        //切割字段
        String[] fields = line.split("\t");
        //手机号
        String phoneNum = fields[1];

        //上传和下载 upFlow downFlow
        long upFlow = Long.parseLong(fields[fields.length - 3]);
        long downFlow = Long.parseLong(fields[fields.length - 2]);

        k.set(phoneNum);
        v.set(upFlow, downFlow);
        context.write(k, v);
    }
}
