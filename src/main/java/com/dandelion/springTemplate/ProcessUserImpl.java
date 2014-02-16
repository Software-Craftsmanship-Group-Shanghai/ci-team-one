package com.dandelion.springTemplate;

import java.util.List;

public class ProcessUserImpl implements ProcessUser {

	static final String PATH = "user.txt";
	public void registerUser(String username, String password) {
		String newContent = username + "|" + password + "|" + "0";
		String path = PATH;
		FileProcessUtil.writeFile(newContent, path);

	}

	public void activateUser(String username) {
		// TODO Auto-generated method stub

	}

	public boolean validateUserIsEffictive(String username, String password) {

		List<String[]> fileContent = FileProcessUtil
				.readFileContentByOneLine(PATH);
		for (int i = 0; i < fileContent.size(); i++) {
			String oneUserInfo[] = fileContent.get(i);
			if (username.equals(oneUserInfo[0])&&password.equals(oneUserInfo[1])) {
				return true;
			}
		}
		return false;
	}

	private boolean userHasExist(String username) {
		List<String[]> fileContent = FileProcessUtil
				.readFileContentByOneLine("");
		for (int i = 0; i < fileContent.size(); i++) {
			String oneUserInfo[] = fileContent.get(i);
			if (username.equals(oneUserInfo[0])) {
				return true;
			}
		}
		return false;
	}

}
