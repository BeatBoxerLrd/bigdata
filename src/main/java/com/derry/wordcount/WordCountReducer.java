package com.derry.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author:derry
 * @Description:
 * @Date: Created in 17:42 2021/7/18
 * @Modified By:
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    protected void reduce(Text arg0, Iterable<IntWritable> arg1,Context arg2)
            throws IOException, InterruptedException {
        int sum = 0;
        for(IntWritable i : arg1){
            sum += i.get();
        }
        arg2.write(arg0, new IntWritable(sum));
    }

}
