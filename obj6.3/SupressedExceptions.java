public class SupressedExceptions {
	public final static void main(String args[]) {
		try {
			oldStyle();
			//newStyle();
		} catch (Exception e) {
			System.out.println(e);
			Throwable[] suppressed = e.getSuppressed();
			if (suppressed.length == 0) {
				System.out.println("Here are no suppressed exceptions");				
			} else {
				System.out.println("List of suppressed exceptions:");
				for (Throwable t : suppressed) {
					System.out.println(t);
				}
			}
		}
	}

	//try to rethrow java.lang.RuntimeException: cannot be used: oldReason
	//finally
	//java.lang.Exception: cannot close: oldReason
	//Here are no suppressed exceptions
	static void oldStyle() throws Exception {
		Resource res = new Resource("oldReason");
		try {
			res.use();
		} catch (Exception e) {
			System.out.println(String.format("try to rethrow %s", e.toString()));
			throw e;
		} finally {
			System.out.println("finally");
			if (res != null) {
				res.close();
			}
		}
	}

	//finally
	//java.lang.RuntimeException: cannot be used: reason 1 
	//List of suppressed exceptions:
	//java.lang.Exception: cannot close: reason 2
	//java.lang.Exception: cannot close: reason 1
	static void newStyle() throws Exception {
		try (
				Resource res1 = new Resource("reason 1"); 
				Resource res2 = new Resource("reason 2")) {
			res1.use();
			res2.use();
		} finally {
			System.out.println("finally");
		}
	}
}

class Resource implements AutoCloseable {
	private String reason;

	Resource(String reason) {
		this.reason = reason;
	}

	public void use() {
		throw new RuntimeException(String.format("cannot be used: %s", reason));
	}

	public void close() throws Exception {
		throw new Exception(String.format("cannot close: %s", reason));
	}
}