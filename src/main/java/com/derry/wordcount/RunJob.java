package com.derry.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author:derry
 * @Description:
 * @Date: Created in 17:43 2021/7/18
 * @Modified By:
 */
public class RunJob {

    public static void main(String[] args) {
        Configuration config = new Configuration();
        try {
            FileSystem fs = FileSystem.get(config);
            Job job = Job.getInstance(config);
            job.setJobName("wordCount");
            job.setJarByClass(RunJob.class);
            job.setMapperClass(WordCountMapper.class);
            job.setReducerClass(WordCountReducer.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);

            FileInputFormat.addInputPath(job, new Path(args[0]));
            Path outPath = new Path(args[1]);
            if (fs.exists(outPath)) {
                fs.delete(outPath, true);
            }
            FileOutputFormat.setOutputPath(job, outPath);
            Boolean result = job.waitForCompletion(true);
            if (result) {
                System.out.println("Job is complete!");
            } else {
                System.out.println("Job is fail!");
            }
        } catch (Exception e) {
            System.out.println("runJob exception:" + e.getMessage());
        }
    }
}
