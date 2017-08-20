package pl.understandable.understandable_dev_app.data.enums.grammar;

public enum GrammarSet {

    TENSES_PRESENT("tenses_present", "czasy teraźniejsze"),
    TENSES_PAST("tenses_past", "czasy przeszłe"),
    TENSES_PERFECT("tenses_perfect", "czasy perfect"),
    TENSES_FUTURE("tenses_future", "czasy przyszłe"),
    PASSIVE_VOICE("passive_voice", "strona bierna"),
    REPORTED_SPEECH("reported_speech", "mowa zależna"),
    CONDITIONALS("conditionals", "zdania warunkowe"),
    MODAL_VERBS("modal_verbs", "czasownki modalne"),
    NOUNS("nouns", "rzeczowniki"),
    VERBS("verbs", "czasowniki"),
    ADJECTIVES("adjectives", "przymiotniki"),
    ADVERBS("adverbs", "przysłówki"),
    PRONOUNS("pronouns", "zaimki"),
    WISHES("wishes", "konstrukcja wish, if only"),
    POSSESIVE_PRONOUNS("possesive_pronouns", "zaimki dzierżawcze"),
    ARTICLES("articles", "przedimki"),
    PREPOSITIONS("prepositions", "przyimki"),
    HAVE_SOMETHING_DONE("have_something_done", "konstrukcja have sth done");

    private String id;
    private String name;

    private GrammarSet(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
