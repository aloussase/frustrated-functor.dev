use clap::{Arg, Command};

#[derive(Debug)]
pub enum BlogCommand {
    AddBook {
        title: String,
        author: String,
        status: String,
        tags: Vec<String>,
    },
}

pub struct InvalidCommand(pub String);

pub fn parse_args() -> Result<BlogCommand, InvalidCommand> {
    let add_book_command = Command::new("add")
        .about("Add a new book")
        .arg(
            Arg::new("title")
                .long("title")
                .short('t')
                .help("The book title")
                .required(true),
        )
        .arg(
            Arg::new("author")
                .long("author")
                .short('a')
                .help("The book author")
                .required(true),
        )
        .arg(
            Arg::new("status")
                .long("status")
                .short('s')
                .help("The book status")
                .required(true),
        )
        .arg(
            Arg::new("tags")
                .long("tags")
                .help("The book tags")
                .long_help("Should be a command separated list of tags.")
                .required(false)
                .default_missing_value(""),
        );

    let books_command = Command::new("books")
        .about("Commands related to book management")
        .subcommand(add_book_command);

    let matches = Command::new("blog")
        .author("Alexander Goussas goussasalexander@gmail.com")
        .version("0.1.0")
        .about("Tool to manage blog content")
        .subcommands(vec![books_command])
        .get_matches();

    match matches.subcommand() {
        Some(("books", args)) => match args.subcommand() {
            Some(("add", args)) => Ok(BlogCommand::AddBook {
                author: args.get_one::<String>("author").unwrap().into(),
                title: args.get_one::<String>("title").unwrap().into(),
                status: args.get_one::<String>("status").unwrap().into(),
                tags: args
                    .get_one::<String>("tags")
                    .map(|s| s.split(",").map(|s| s.to_string()).collect())
                    .unwrap_or_else(|| Vec::new()),
            }),
            _ => Err(InvalidCommand("Invalid books subcommand".into())),
        },
        _ => Err(InvalidCommand("Invalid top-level command".into())),
    }
}
