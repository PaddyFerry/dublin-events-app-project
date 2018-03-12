from __future__ import division
from faceScrape import FacebookScraper
import facebook, sys, eventClass
from datetime import date
from datetime import datetime
from os import path
sys.path.append(path.dirname(path.dirname(path.abspath(__file__))))


def run():

    face = FacebookScraper(facebook.GraphAPI(access_token="""EAANzGl7D69MBAEzqZB5n2oAFSxzf6YrY3bDgJoXSdmNNn73v4
                                                            mtwCZCw6qQmuRY78n3QZAmBuyMRePFo4LJCdOE3mCHVcDOZB0ot
                                                            FKjFOZCO3cja9Km4ZAF2eciXlFIKr536h6gLwZBJJZBZCTdBlNK
                                                            CBrAGEiVzxwonBJgA9bIrO9wZDZD""",
                                             version="2.1"))
    pub_ids = zip(*face.extract_ids("out.txt"))
    for pub_name, pub_id in pub_ids:
        events = face.get_events(pub_id)
        if len(events) != 0:

            for event in events["data"]:
                event_date_and_time = face.get_date_and_time(event["start_time"].split("T"))
                num_days = face.date_diff(event_date_and_time[0])

                if num_days >= 0 and num_days <= 20:
                    if event.get("description"):
                        desc = event.get("description")[:500] if len(event.get("description")) > 499 else event.get("description")
                    else:
                        desc = ""
                    datetime = " ".join(event_date_and_time)
                    yield (event.get("name"), pub_name, event.get("ticker_uri"), desc, datetime, "")







