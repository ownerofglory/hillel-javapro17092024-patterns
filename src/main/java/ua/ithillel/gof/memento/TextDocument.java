package ua.ithillel.gof.memento;

public class TextDocument implements Persistable {
    private String text;
    private String title;

    private ChangeHistory changeHistory = new ChangeHistory();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ChangeHistory getChangeHistory() {
        return changeHistory;
    }

    @Override
    public void persist() {
        TextDocument textDocument = new TextDocument();
        textDocument.text = this.text;
        textDocument.title = this.title;

        this.changeHistory.save(textDocument);
    }

    @Override
    public void restore() {
        Persistable undo = changeHistory.undo();
        if (undo instanceof TextDocument) {
            this.text = ((TextDocument) undo).text;
            this.title = ((TextDocument) undo).title;
        }
    }
}
