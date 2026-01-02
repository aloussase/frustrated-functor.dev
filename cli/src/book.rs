use serde::Serialize;

#[derive(Serialize, Debug)]
pub struct Book {
    pub author: String,
    pub title: String,
    pub status: String,
    pub tags: Vec<String>,
}
