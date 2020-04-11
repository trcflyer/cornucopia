package com.easypay.cornucopiaallqrpay.biz;


import com.easypay.cornucopiaallqrpay.dal.dao.impl.SequenceMapperImpl;
import com.easypay.cornucopiaallqrpay.dal.pojo.Sequence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class SequenceBiz{

    @Autowired
    private SequenceMapperImpl sequenceMapperImpl;

    public Map<String, LinkedBlockingQueue<Long>> seqListMap = new HashMap<>();
    private LinkedBlockingQueue<Long> queue;
    private static volatile boolean flag = true;


    /**
     * 获取指定序列号的下一个id
     *
     * @param seqIdName
     * @return
     * @throws Exception
     */
    public String getSeqId(String seqIdName) {
        synchronized (SequenceBiz.class) {
            queue = seqListMap.get(seqIdName);
            if (queue == null) {
                queue = new LinkedBlockingQueue();
                getData(queue, seqIdName);
                seqListMap.put(seqIdName, queue);
            } else if (queue.size() < 100) {
                if(flag){
                    flag = false;
                    //缓存id不足时候，启动线程获取数据
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            getData(queue, seqIdName);
                        }
                    }.start();
                }else {
                    log.info("线程已经在缓存id");
                }
            }

            /**
             * take()：首选。当队列为空时阻塞
             * poll()：弹出队顶元素，队列为空时，返回空
             * peek()：和poll烈性，返回队队顶元素，但顶元素不弹出。队列为空时返回null
             * remove(Object o)：移除某个元素，队列为空时抛出异常NoSuchElementException。成功移除返回true
             */
            Long result = null;
            try {
                result = queue.poll(200L,TimeUnit.MILLISECONDS);
                if(result == null)
                {
                    log.error("获取序列号超时失败");
                }
            }catch (Exception e){
                log.error("获取序列号超时异常");
                log.error(e.getMessage());
            }

            return String.valueOf(result);
        }
    }

    private void getData(LinkedBlockingQueue<Long> queue, String seqIdName) {
        Sequence quickPaySequence = sequenceMapperImpl.selectBySeqName(seqIdName);
        if (quickPaySequence == null) {
            log.error("不存在,查询序列号失败");
            flag =true;
            return;
        }

        try {
            int newCacheSize = quickPaySequence.getIncrementBy(); // 数据库查询当前序列号的步长
            long nextSeqid = queryNextSeqId(seqIdName);
            if (nextSeqid == -1){
                flag =true;
                return ;
            }

            for (int i = 0; i < newCacheSize; i++) {
                /**
                 * put()：首选。队满是阻塞
                 * offer()：队满时返回false
                 * add（）：队满时抛出异常IllegalStateException
                 */
                queue.put(nextSeqid + i);
            }
        } catch (Exception e) {
            flag =true;
            log.error(seqIdName + "获取序列号失败", e);
        }
        flag =true;
    }

    private long queryNextSeqId(String seqIdName) throws Exception{
        int cnt = 0;
        while (cnt < 10) {
            Sequence quickPaySequence = sequenceMapperImpl.selectBySeqName(seqIdName); // 数据库查询当前序列号的下一个id

            long nextValue = quickPaySequence.getNextValue();
            long minValue = quickPaySequence.getMinValue();
            long max_value = quickPaySequence.getMaxValue();
            Integer increment_by = quickPaySequence.getIncrementBy();
            Integer version = quickPaySequence.getVersion();

            long nextSeqid = nextValue;
            if (nextValue + increment_by >= max_value) {
                nextSeqid = minValue;
            }

            if (nextValue + increment_by >= max_value) {
                nextValue = minValue + increment_by;
            }else{
                nextValue = nextValue + increment_by;
            }
            int i = sequenceMapperImpl.updateBySeqNameAndVersion(seqIdName,nextValue,version);
            if (i == 0) {
                log.info("序列号占用失败，" + seqIdName);
                Thread.sleep(10);

                cnt = cnt + 1;
                continue;
            }
            return nextSeqid;
        }

        log.error("序列号占用失败，" + seqIdName);
        return -1;
    }

}
