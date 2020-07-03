package de.edgelord.codescrambler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.util.Random;

// ATTENTION: BORING
public class UnscrambledMainATTENTION$BORING {
    private static final int MAX_SPACE_SHIFT = 10;
    private static final int MAX_NEWLINES = 1;
    private static final Random rng = new SecureRandom();

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("ERROR: missing argument: file path");
            System.exit(1);
        }
        File file = new File(args[0]);
        String fileContent = String.join("\n", Files.readAllLines(file.toPath()));

        File unscrambledFile = new File(file.getParentFile(), file.getName() + ".unscrambled");
        unscrambledFile.createNewFile();
        Files.write(unscrambledFile.toPath(), fileContent.getBytes());

        // scramble the code
        StringBuilder scrambledCode = new StringBuilder();

        boolean insideString = false;
        boolean nextCharEscaped = false;
        int currentSpaceShift = 0;
        for (char c : fileContent.toCharArray()) {
            if (c == '\\' && !nextCharEscaped) {
                nextCharEscaped = true;
            }
            if (insideString) {
                if (!nextCharEscaped && (c == '"' || c == '\'')) {
                    insideString = false;
                }
                nextCharEscaped = false;
            } else {
                if (c == '"') {
                    insideString = true;
                } else if (c == '(' || c == ')' || c == '}' || c == '{' || c == ';' || c == ' ') {
                    int nextSpaceShift = rng.nextInt(MAX_SPACE_SHIFT);
                    if (rng.nextBoolean()) {
                        nextSpaceShift *= -1;
                    }
                    scrambledCode.append(new String(new char[Math.max(nextSpaceShift + currentSpaceShift, 0)]).replace("\0", " "));
                    currentSpaceShift += nextSpaceShift;
                    scrambledCode.append(new String(new char[rng.nextInt(MAX_NEWLINES)]).replace("\0", "\n"));
                }
            }
            scrambledCode.append(c);
        }

        Files.write(file.toPath(), scrambledCode.toString().getBytes());
    }

}
