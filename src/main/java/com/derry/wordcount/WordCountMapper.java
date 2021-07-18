package com.derry.wordcount;

import org.apache.hadoop.util.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author:derry
 * @Description:
 * @Date: Created in 17:36 2021/7/18
 * @Modified By:
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String[] words = StringUtils.split(value.toString(), ' ');
        for (String word : words) {
            context.write(new Text(word), new IntWritable(1));
        }
    }
}
