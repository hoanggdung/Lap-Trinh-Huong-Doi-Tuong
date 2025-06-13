import java.util.*;

class Note {
    private int id;
    private String content;
    private String dateStr;

    public Note(int id, String dateStr, String content) {
        this.id = id;
        this.dateStr = dateStr;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getDateStr() {
        return dateStr;
    }

    public String getFormattedDate() {
        if (dateStr == null || dateStr.length() != 7) return dateStr;
        try {
            String monthStr = dateStr.substring(0, 3);
            String dayStr = dateStr.substring(3, 5);
            String yearStr = dateStr.substring(5, 7);
            return String.format("%s %s %s", dayStr, monthStr, yearStr);
        } catch (IndexOutOfBoundsException e) {
            return dateStr;
        }
    }

    @Override
    public String toString() {
        return "Note{" + "id=" + id + ", date='" + getFormattedDate() + '\'' + ", content='" + content + '\'' + '}';
    }
}

class Account {
    private String id;
    private String accountName;
    private List<Note> notes;
    private int nextNoteId;
    private static int nextAccountId = 1;

    public Account(String accountName) {
        this.id = String.format("%03d", nextAccountId++);
        this.accountName = accountName;
        this.notes = new ArrayList<>();
        this.nextNoteId = 1;
    }

    public static void resetAccountCounter() {
        nextAccountId = 1;
    }

    public String getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void addNote(String dateStr, String content) {
        Note newNote = new Note(this.nextNoteId, dateStr, content);
        this.notes.add(newNote);
        this.nextNoteId++;
    }

    public Note findNoteById(int noteId) {
        for (Note note : this.notes) {
            if (note.getId() == noteId) {
                return note;
            }
        }
        return null;
    }

    public void receiveSharedNote(Note noteToReceive) {
        if (noteToReceive != null) {
            for (Note existingNote : this.notes) {
                if (existingNote == noteToReceive) return;
            }
            this.notes.add(noteToReceive);
            this.nextNoteId++;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Account> accountsById = new HashMap<>();
        List<Account> accountsInOrder = new ArrayList<>();
        Account.resetAccountCounter();
        boolean validInput = true;

        try {
            if (!scanner.hasNextLine()) {
                System.out.println("invalid input");
                scanner.close();
                return;
            }
            String nStr = scanner.nextLine();
            int n;
            try {
                n = Integer.parseInt(nStr);
                if (n < 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("invalid input");
                scanner.close();
                return;
            }

            for (int i = 0; i < n; i++) {
                if (!scanner.hasNextLine()) {
                    validInput = false;
                    break;
                }
                String accountName = scanner.nextLine();
                if (accountName == null || accountName.trim().isEmpty()) {
                    validInput = false;
                    break;
                }
                Account newAccount = new Account(accountName);
                accountsById.put(newAccount.getId(), newAccount);
                accountsInOrder.add(newAccount);
            }

            while (validInput && scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line == null || line.trim().isEmpty()) continue;

                String[] parts = line.split("\\s+", 3);
                if (parts.length < 3) {
                    validInput = false;
                    break;
                }

                String accountId = parts[0];
                String command = parts[1];
                String contentPart = parts[2];
                Account sourceAccount = accountsById.get(accountId);
                if (sourceAccount == null) {
                    validInput = false;
                    break;
                }

                if ("Add".equalsIgnoreCase(command)) {
                    String[] addParts = contentPart.split("\\s+", 2);
                    if (addParts.length < 2) {
                        validInput = false;
                        break;
                    }
                    String dateStr = addParts[0];
                    String noteContent = addParts[1];
                    if (dateStr.length() != 7) {
                        validInput = false;
                        break;
                    }
                    sourceAccount.addNote(dateStr, noteContent);
                } else if ("Share".equalsIgnoreCase(command)) {
                    String[] shareParts = contentPart.split("\\s+", 2);
                    if (shareParts.length < 2) {
                        validInput = false;
                        break;
                    }
                    String targetAccountId = shareParts[0];
                    String noteIdStr = shareParts[1];
                    Account targetAccount = accountsById.get(targetAccountId);
                    if (targetAccount == null) {
                        validInput = false;
                        break;
                    }
                    try {
                        int noteId = Integer.parseInt(noteIdStr);
                        if (noteId <= 0) {
                            validInput = false;
                            break;
                        }
                        Note noteToShare = sourceAccount.findNoteById(noteId);
                        if (noteToShare == null) continue;
                        targetAccount.receiveSharedNote(noteToShare);
                    } catch (NumberFormatException e) {
                        validInput = false;
                        break;
                    }
                } else {
                    validInput = false;
                    break;
                }
            }

            if (!validInput) {
                System.out.println("invalid input");
            } else {
                for (Account acc : accountsInOrder) {
                    System.out.println("Account: " + acc.getAccountName());
                    List<Note> notes = acc.getNotes();
                    notes.sort(Comparator.comparingInt(Note::getId));
                    for (Note note : notes) {
                        System.out.println(note.getFormattedDate() + " | " + note.getContent());
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("invalid input");
        } finally {
            if (scanner != null) scanner.close();
        }
    }
}
