
public class ThreadJVM {

	ThreadGroup rootGroup;

	public ThreadJVM() {

	}

	public void getRoot() {
		
		//Get the root group - System
		ThreadGroup parent;
		rootGroup = Thread.currentThread().getThreadGroup();

		while ((parent = rootGroup.getParent()) != null) {
			rootGroup = parent;
		}

		System.out.println("\nActive threads in the JVM:\n");
		listThreads(rootGroup);

	}

	
	//This is the test method, the threads are created here and should
	//be displayed once the display threads button is clicked
	public void createThreads() {

		System.out.println("\nNew Threads Created...");
		ThreadGroup a = new ThreadGroup("A");
		ThreadGroup b = new ThreadGroup("B");
		ThreadGroup c = new ThreadGroup(a, "C");
		ThreadGroup d = new ThreadGroup(c, "D");

		(new Thread(new DoWork())).start();
		(new Thread(a, new DoWork())).start();
		(new Thread(a, new DoWork())).start();
		(new Thread(a, new DoWork())).start();
		(new Thread(b, new DoWork())).start();
		(new Thread(c, new DoWork())).start();
		(new Thread(c, new DoWork())).start();
		(new Thread(d, new DoWork(), "Hello Thread")).start();

	}

	public void listThreads(ThreadGroup group) {
		
		System.out.println("\n Group[" + group.getName() + "]" + 
		"--parent group is " + group.getParent() + "\n"); //print current group
																													
		int nThreads = group.activeCount(); //n total threads in this group and its sub groups

		Thread[] threads = new Thread[nThreads * 2 + 10]; // nThreads is not accurate atm!- likely to be much larger than needed
		nThreads = group.enumerate(threads, false); //n threads in this group

		// list all the threads in the group
		for (int i = 0; i < nThreads; i++) {
			Thread t = threads[i];
			System.out.println("  Thread[" + t.getName() + "]" + "\n\tID - " + t.getId() + "\n\tState - " + t.getState()
					+ "\n\tPriority - " + t.getPriority() + "\n\tDaemon -  " + t.isDaemon());
		}
		//recursively list subgroups
		int nGroups = group.activeGroupCount(); //n child groups of this group and its subgroups

		ThreadGroup[] groups = new ThreadGroup[nGroups * 2 + 10];
		nGroups = group.enumerate(groups, false);// n child groups

		
		for (int i = 0; i < nGroups; i++) {
			listThreads(groups[i]);
		}
	}

}