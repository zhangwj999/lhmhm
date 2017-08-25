package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ConvertVideo {

	private static String inputPath = "";

	private static String outputPath = "";

	private static String ffmpegPath = "";

	public static void main(String args[]) throws IOException {

		getPath();

		if (!checkfile(inputPath)) {
			System.out.println(inputPath + " is not file");
			return;
		}
		if (process()) {
			System.out.println("ok");
		}
		
	}

	private static void getPath() { // 先获取当前项目路径，在获得源文件、目标文件、转换器的路径
		File diretory = new File("");
		try {
			String currPath = diretory.getAbsolutePath();
//			inputPath = currPath + "\\input\\test.wmv";
//			outputPath = currPath + "\\output\\";
//			ffmpegPath = currPath + "\\ffmpeg\\";
			inputPath = "D:\\ffmpeg\\input\\video.mp4";
			outputPath = "D:\\ffmpeg\\output\\";
			ffmpegPath = "D:\\ffmpeg\\";
			System.out.println(currPath);
		} catch (Exception e) {
			System.out.println("getPath出错");
		}
	}

	private static boolean process() {
		int type = checkContentType();
		boolean status = false;
		if (type == 0) {
			System.out.println("直接转成flv格式");
			//status = processFLV(inputPath);// 直接转成flv格式
			//截取视频
			status=subVideo(inputPath);
		} else if (type == 1) {
			String avifilepath = processAVI(type);
			if (avifilepath == null)
				return false;// 没有得到avi格式
			status = processFLV(avifilepath);// 将avi转成flv格式
		}
		return status;
	}

	private static int checkContentType() {
		String type = inputPath.substring(inputPath.lastIndexOf(".") + 1,
				inputPath.length()).toLowerCase();
		// ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
		if (type.equals("avi")) {
			return 0;
		} else if (type.equals("mpg")) {
			return 0;
		} else if (type.equals("wmv")) {
			return 0;
		} else if (type.equals("3gp")) {
			return 0;
		} else if (type.equals("mov")) {
			return 0;
		} else if (type.equals("mp4")) {
			return 0;
		} else if (type.equals("asf")) {
			return 0;
		} else if (type.equals("asx")) {
			return 0;
		} else if (type.equals("flv")) {
			return 0;
		}
		// 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),
		// 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
		else if (type.equals("wmv9")) {
			return 1;
		} else if (type.equals("rm")) {
			return 1;
		} else if (type.equals("rmvb")) {
			return 1;
		}
		return 9;
	}

	private static boolean checkfile(String path) {
		File file = new File(path);
		if (!file.isFile()) {
			return false;
		}
		return true;
	}

	// 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等), 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
	private static String processAVI(int type) {
		List<String> commend = new ArrayList<String>();
		commend.add(ffmpegPath + "mencoder");
		commend.add(inputPath);
		commend.add("-oac");
		commend.add("lavc");
		commend.add("-lavcopts");
		commend.add("acodec=mp3:abitrate=64");
		commend.add("-ovc");
		commend.add("xvid");
		commend.add("-xvidencopts");
		commend.add("bitrate=600");
		commend.add("-of");
		commend.add("avi");
		commend.add("-o");
		commend.add(outputPath + "a.avi");
		try {
			ProcessBuilder builder = new ProcessBuilder();
			Process process = builder.command(commend)
					.redirectErrorStream(true).start();
			new PrintStream(process.getInputStream());
			new PrintStream(process.getErrorStream());
			process.waitFor();
			return outputPath + "a.avi";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
	private static boolean processFLV(String oldfilepath) {

		if (!checkfile(inputPath)) {
			System.out.println(oldfilepath + " is not file");
			return false;
		}

		List<String> command = new ArrayList<String>();
		command.add(ffmpegPath + "ffmpeg");
		command.add("-i");
		command.add(oldfilepath);
		command.add("-ab");
		command.add("56");
		command.add("-ar");
		command.add("22050");
		command.add("-qscale");
		command.add("8");
		command.add("-r");
		command.add("15");
		command.add("-s");
		command.add("600x500");
		command.add(outputPath + "a.flv");

		try {

			// 方案1
			// Process videoProcess = Runtime.getRuntime().exec(ffmpegPath +
			// "ffmpeg -i " + oldfilepath
			// + " -ab 56 -ar 22050 -qscale 8 -r 15 -s 600x500 "
			// + outputPath + "a.flv");

			// 方案2
			Process videoProcess = new ProcessBuilder(command)
					.redirectErrorStream(true).start();

			new PrintStream(videoProcess.getErrorStream()).start();

			new PrintStream(videoProcess.getInputStream()).start();

			videoProcess.waitFor();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//截取视频
	private static boolean subVideo(String oldfilepath){
		if (!checkfile(inputPath)) {
			System.out.println(oldfilepath + " is not file");
			return false;
		}

		List<String> command = new ArrayList<String>();
		command.add(ffmpegPath + "ffmpeg");
		command.add("-ss");
		command.add("00:00:30");
		
		command.add("-i");
		command.add(oldfilepath);
		command.add("-vcodec");
		command.add("copy");
		command.add("-acodec");
		command.add("copy");
		
		command.add("-t");
		command.add("00:00:15");	
		command.add(outputPath + "sub.mp4");
	
		try {

			// 方案1
			// Process videoProcess = Runtime.getRuntime().exec(ffmpegPath +
			// "ffmpeg -i " + oldfilepath
			// + " -ab 56 -ar 22050 -qscale 8 -r 15 -s 600x500 "
			// + outputPath + "a.flv");

			// 方案2
			Process videoProcess = new ProcessBuilder(command)
					.redirectErrorStream(true).start();
			
			new PrintStream(videoProcess.getErrorStream()).start();

			new PrintStream(videoProcess.getInputStream()).start();

			videoProcess.waitFor();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

class PrintStream extends Thread {
	java.io.InputStream __is = null;

	public PrintStream(java.io.InputStream is) {
		__is = is;
	}

	public void run() {
		try {
			while (this != null) {
				int _ch = __is.read();
				if (_ch != -1)
					System.out.print((char) _ch);
				else
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*public TEquipmentTree[] getChildren(String treeNodeId) throws Exception {
		  Connection conn = getConnection();
		  PreparedStatement smt = conn.prepareStatement("select * from t_equipmentclass where eql_pcode ='"+treeNodeId+"'");
		  
		  ResultSet rs = smt.executeQuery();
		  List<TEquipmentTree> childList = new ArrayList<TEquipmentTree>();
		  while (rs.next()) {
		   TEquipmentTree TEquipmentTree = new TEquipmentTree();
		   TEquipmentTree.setEqlCode(rs.getString("eql_code"));
		   TEquipmentTree.setEqlName(rs.getString("eql_name"));
		   TEquipmentTree.setEqlPcode(rs.getString("eql_pcode"));
		   TEquipmentTree.setEqlLevel(rs.getInt("eql_level"));
		   TEquipmentTree.setEqlRemark(rs.getString("eql_remark"));
		   TEquipmentTree.setChildEquipment(getChildren(TEquipmentTree.getEqlCode()));
		   childList.add(TEquipmentTree);
		  }
		  rs.close();
		  smt.close();
		  conn.close();
		  TEquipmentTree[] childResult = new TEquipmentTree[childList.size()];
		  childResult = childList.toArray(childResult);
		  return childResult;
		 }

		public TEquipmentTree[] getAllTEquipmentTree() throws Exception {
		  String sql="select * from T_Equipmentclass where EQL_PCODE='EEW1001'";
		  Connection conn = getConnection();
		  PreparedStatement smt = conn.prepareStatement(sql);
		  ResultSet rs = smt.executeQuery();
		  List<TEquipmentTree> TEquipmentTreeList = new ArrayList<TEquipmentTree>();
		  while (rs.next()) {
		   TEquipmentTree TEquipmentTree = new TEquipmentTree();
		   TEquipmentTree.setEqlCode(rs.getString("EQL_CODE"));
		   TEquipmentTree.setEqlName(rs.getString("EQL_NAME"));
		   TEquipmentTree.setEqlPcode(rs.getString("EQL_PCODE"));
		   TEquipmentTree.setEqlLevel(rs.getInt("EQL_LEVEL"));
		   TEquipmentTree.setEqlRemark(rs.getString("EQL_REMARK"));
		   TEquipmentTree.setChildEquipment(getChildren(TEquipmentTree.getEqlCode()));
		   TEquipmentTreeList.add(TEquipmentTree);

		  }
		  rs.close();
		  smt.close();
		  conn.close();
		  TEquipmentTree[] TEquipmentTree = new TEquipmentTree[TEquipmentTreeList.size()];
		  TEquipmentTree = TEquipmentTreeList.toArray(TEquipmentTree);
		  return TEquipmentTree;
		 }*/

}