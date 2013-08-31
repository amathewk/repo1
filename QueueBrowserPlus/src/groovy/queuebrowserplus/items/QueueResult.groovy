package queuebrowserplus.items

class QueueResult implements Serializable {
	List<QueueItem> items = new ArrayList<QueueItem>()
	int queueCount // total queue count
	
	public QueueResult plus(QueueResult queueResult) {
		QueueResult totalQueueResult = new QueueResult()
		totalQueueResult.items.addAll(items)
		totalQueueResult.items.addAll(queueResult.items)
		totalQueueResult.queueCount = Math.max(queueCount, queueResult.queueCount)
		return totalQueueResult
	}

}
